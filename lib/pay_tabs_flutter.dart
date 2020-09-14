import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:pay_tabs_flutter/pay_tabs_language.dart';
import 'package:pay_tabs_flutter/pay_tabs_payment_result.dart';

const MethodChannel _channel = const MethodChannel('pay_tabs_flutter');

Future<PayTabsPaymentResult> openPayTabsPayment({
  @required String merchantEmail,
  @required String secretKey,
  PayTabsLanguage language,
  String transactionTitle,
  double amount,
  String currencyCode,
  String customerPhoneNumber,
  String customerEmail,
  String orderId,
  String productName,
  String addressBilling,
  String cityBilling,
  String stateBilling,
  String countryBilling,
  String postalCodeBilling,
  String addressShipping,
  String cityShipping,
  String stateShipping,
  String countryShipping,
  String postalCodeShipping,
  String payButtonColor,
  bool isTokenization,
}) async {
  final Map<String, String> resultMap = await _channel.invokeMapMethod(
    'openPayment',
    <String, dynamic>{
      'merchantEmail': merchantEmail,
      'secretKey': secretKey,
      'language': language.toShortString(),
      'transactionTitle': transactionTitle,
      'amount': amount,
      'currencyCode': currencyCode,
      'customerPhoneNumber': customerPhoneNumber,
      'customerEmail': customerEmail,
      'orderId': orderId,
      'productName': productName,
      'addressBilling': addressBilling,
      'cityBilling': cityBilling,
      'stateBilling': stateBilling,
      'countryBilling': countryBilling,
      'postalCodeBilling': postalCodeBilling,
      'addressShipping': addressShipping,
      'cityShipping': cityShipping,
      'stateShipping': stateShipping,
      'countryShipping': countryShipping,
      'postalCodeShipping': postalCodeShipping,
      'payButtonColor': payButtonColor,
      'isTokenization': isTokenization,
    },
  );

  return PayTabsPaymentResult.fromJson(resultMap);
}
