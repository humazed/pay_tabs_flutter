import 'package:json_annotation/json_annotation.dart';

part 'pay_tabs_payment_result.g.dart';

@JsonSerializable()
class PayTabsPaymentResult {
  String responseCode;
  String transactionId;

  String token;
  String customerEmail;
  String customerPasswordc;

  static const fromJsonFactory = _$PayTabsPaymentResultFromJson;

  PayTabsPaymentResult();

  factory PayTabsPaymentResult.fromJson(Map<String, dynamic> json) =>
      _$PayTabsPaymentResultFromJson(json);

  Map<String, dynamic> toJson() => _$PayTabsPaymentResultToJson(this);

  @override
  String toString() {
    return 'PayTabsPaymentResult{responseCode: $responseCode, transactionId: $transactionId, token: $token, customerEmail: $customerEmail, customerPasswordc: $customerPasswordc}';
  }
}
