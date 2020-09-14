import 'package:flutter/material.dart';
import 'package:pay_tabs_flutter/pay_tabs_flutter.dart';
import 'package:pay_tabs_flutter/pay_tabs_language.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Future<void> openPayment() async {
    final result = await openPayTabsPayment(
      merchantEmail: "",
      secretKey:
          "",
      language: PayTabsLanguage.en,
      transactionTitle: "Test Paytabs android library",
      amount: 5.0,
      currencyCode: "SAR",
      customerPhoneNumber: "009733",
      customerEmail: "customer-email@example.com",
      orderId: "123456",
      productName: "Product 1, Product 2",
      addressBilling: "Flat 1,Building 123, Road 2345",
      cityBilling: "Manama",
      stateBilling: "Manama",
      countryBilling: "SAR",
      postalCodeBilling: "00973",
      addressShipping: "Flat 1,Building 123, Road 2345",
      cityShipping: "Manama",
      stateShipping: "Manama",
      countryShipping: "SAR",
      postalCodeShipping: "00973",
      payButtonColor: "#2474bc",
      isTokenization: false,
    );

    print("result = $result");
  }

  Future<void> openPaymentCE() async {
    final result = await openPayTabsPayment(
      merchantEmail: "",
      secretKey:
          "",
      language: PayTabsLanguage.en,
      transactionTitle: "Test Paytabs android library",
      amount: 5.0,
      currencyCode: "EGP",
      customerPhoneNumber: "009733",
      customerEmail: "customer-email@example.com",
      orderId: "123456",
      productName: "Product 1, Product 2",
      addressBilling: "Flat 1,Building 123, Road 2345",
      cityBilling: "Manama",
      stateBilling: "Manama",
      countryBilling: "BHR",
      postalCodeBilling: "00973",
      addressShipping: "Flat 1,Building 123, Road 2345",
      cityShipping: "Manama",
      stateShipping: "Manama",
      countryShipping: "EGP",
      postalCodeShipping: "00973",
      payButtonColor: "#2474bc",
      isTokenization: false,
    );

    print("result = $result");
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: RaisedButton(
            onPressed: openPaymentCE,
            child: Text("PAY"),
          ),
        ),
      ),
    );
  }
}
