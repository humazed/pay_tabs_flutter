// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pay_tabs_payment_result.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PayTabsPaymentResult _$PayTabsPaymentResultFromJson(Map<String, dynamic> json) {
  return PayTabsPaymentResult()
    ..responseCode = json['responseCode']?.toString()
    ..transactionId = json['transactionId']?.toString()
    ..token = json['token']?.toString()
    ..customerEmail = json['customerEmail']?.toString()
    ..customerPasswordc = json['customerPasswordc']?.toString();
}

Map<String, dynamic> _$PayTabsPaymentResultToJson(
        PayTabsPaymentResult instance) =>
    <String, dynamic>{
      'responseCode': instance.responseCode,
      'transactionId': instance.transactionId,
      'token': instance.token,
      'customerEmail': instance.customerEmail,
      'customerPasswordc': instance.customerPasswordc,
    };
