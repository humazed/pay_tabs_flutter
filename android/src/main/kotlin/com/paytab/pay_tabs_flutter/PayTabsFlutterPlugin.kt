package com.paytab.pay_tabs_flutter

import android.app.Activity
import android.content.Intent
import androidx.annotation.NonNull
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity
import com.paytabs.paytabs_sdk.utils.PaymentParams
import humazed.github.com.kotlinandroidutils.d
import humazed.github.com.kotlinandroidutils.startActivityForResult
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** PayTabsFlutterPlugin */
class PayTabsFlutterPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private var activityBinding: ActivityPluginBinding? = null

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "pay_tabs_flutter")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "openPayment") {
            val argumentsMaps = call.arguments<Map<String, Any?>>()
            val merchantEmail = argumentsMaps["merchantEmail"] as? String
                    ?: error("merchantEmail is required")
            val secretKey = argumentsMaps["secretKey"] as? String
                    ?: error("secretKey is required")

            activityBinding?.activity?.openPayment(
                    merchantEmail,
                    secretKey,
                    argumentsMaps["language"] as String?,
                    argumentsMaps["transactionTitle"] as String?,
                    argumentsMaps["amount"] as Double?,
                    argumentsMaps["currencyCode"] as String?,
                    argumentsMaps["customerPhoneNumber"] as String?,
                    argumentsMaps["customerEmail"] as String?,
                    argumentsMaps["orderId"] as String?,
                    argumentsMaps["productName"] as String?,
                    argumentsMaps["addressBilling"] as String?,
                    argumentsMaps["cityBilling"] as String?,
                    argumentsMaps["stateBilling"] as String?,
                    argumentsMaps["countryBilling"] as String?,
                    argumentsMaps["postalCodeBilling"] as String?,
                    argumentsMaps["addressShipping"] as String?,
                    argumentsMaps["cityShipping"] as String?,
                    argumentsMaps["stateShipping"] as String?,
                    argumentsMaps["countryShipping"] as String?,
                    argumentsMaps["postalCodeShipping"] as String?,
                    argumentsMaps["payButtonColor"] as String?,
                    argumentsMaps["isTokenization"] as Boolean?
            ) { paymentResult ->
                result.success(paymentResult)
            }
        } else {
            result.notImplemented()
        }
    }


    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }


    // region ActivityAware
    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activityBinding = binding
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        this.activityBinding = binding
    }

    override fun onDetachedFromActivity() {
        this.activityBinding = null

    }

    override fun onDetachedFromActivityForConfigChanges() {
        this.activityBinding = null
    }
    // endregion ActivityAware


    private fun Activity.openPayment(
            merchantEmail: String, secretKey: String,
            language: String?,
            transactionTitle: String?,
            amount: Double?,
            currencyCode: String?,
            customerPhoneNumber: String?,
            customerEmail: String?,
            orderId: String?,
            productName: String?,
            addressBilling: String?,
            cityBilling: String?,
            stateBilling: String?,
            countryBilling: String?,
            postalCodeBilling: String?,
            addressShipping: String?,
            cityShipping: String?,
            stateShipping: String?,
            countryShipping: String?,
            postalCodeShipping: String?,
            payButtonColor: String?,
            isTokenization: Boolean?,
            onResult: (paymentResult: Map<String, String?>) -> Unit
    ) {
        val intent = Intent(applicationContext, PayTabActivity::class.java)

        intent.putExtra(PaymentParams.MERCHANT_EMAIL, merchantEmail)
        intent.putExtra(PaymentParams.SECRET_KEY, secretKey)

        intent.putExtra(PaymentParams.LANGUAGE, language)
        intent.putExtra(PaymentParams.TRANSACTION_TITLE, transactionTitle)
        intent.putExtra(PaymentParams.AMOUNT, amount)

        intent.putExtra(PaymentParams.CURRENCY_CODE, currencyCode)
        intent.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, customerPhoneNumber)
        intent.putExtra(PaymentParams.CUSTOMER_EMAIL, customerEmail)
        intent.putExtra(PaymentParams.ORDER_ID, orderId)
        intent.putExtra(PaymentParams.PRODUCT_NAME, productName)

        //Billing Address
        intent.putExtra(PaymentParams.ADDRESS_BILLING, addressBilling)
        intent.putExtra(PaymentParams.CITY_BILLING, cityBilling)
        intent.putExtra(PaymentParams.STATE_BILLING, stateBilling)
        intent.putExtra(PaymentParams.COUNTRY_BILLING, countryBilling)
        intent.putExtra(PaymentParams.POSTAL_CODE_BILLING, postalCodeBilling) //Put Country Phone code if Postal code not available '00973'

        //Shipping Address
        intent.putExtra(PaymentParams.ADDRESS_SHIPPING, addressShipping)
        intent.putExtra(PaymentParams.CITY_SHIPPING, cityShipping)
        intent.putExtra(PaymentParams.STATE_SHIPPING, stateShipping)
        intent.putExtra(PaymentParams.COUNTRY_SHIPPING, countryShipping)
        intent.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, postalCodeShipping) //Put Country Phone code if Postal code not available '00973'

        //Payment Page Style
        intent.putExtra(PaymentParams.PAY_BUTTON_COLOR, payButtonColor)

        //Tokenization
        intent.putExtra(PaymentParams.IS_TOKENIZATION, isTokenization)

        startActivityForResult(intent) { resultCode, data ->
            val resultMap: Map<String, String?>
            if (resultCode == Activity.RESULT_OK) {
                resultMap = mapOf(
                        "responseCode" to data?.getStringExtra(PaymentParams.RESPONSE_CODE),
                        "transactionId" to data?.getStringExtra(PaymentParams.TRANSACTION_ID),
                        "token" to data?.getStringExtra(PaymentParams.TOKEN),
                        "customerEmail" to data?.getStringExtra(PaymentParams.CUSTOMER_EMAIL),
                        "customerPassword" to data?.getStringExtra(PaymentParams.CUSTOMER_PASSWORD)
                )

                d { "RESPONSE_CODE: " + data?.getStringExtra(PaymentParams.RESPONSE_CODE) }
                d { "TRANSACTION_ID: " + data?.getStringExtra(PaymentParams.TRANSACTION_ID) }
                d { "TOKEN: " + data?.getStringExtra(PaymentParams.TOKEN) }
                d { "CUSTOMER_EMAIL: " + data?.getStringExtra(PaymentParams.CUSTOMER_EMAIL) }
                d { "CUSTOMER_PASSWORD: " + data?.getStringExtra(PaymentParams.CUSTOMER_PASSWORD) }
            } else {
                resultMap = emptyMap()
            }

            onResult(resultMap)
        }
    }


}
