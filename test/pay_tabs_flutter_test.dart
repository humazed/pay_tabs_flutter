//import 'package:flutter/services.dart';
//import 'package:flutter_test/flutter_test.dart';
//import 'package:pay_tabs_flutter/pay_tabs_flutter.dart';
//
//void main() {
//  const MethodChannel channel = MethodChannel('pay_tabs_flutter');
//
//  TestWidgetsFlutterBinding.ensureInitialized();
//
//  setUp(() {
//    channel.setMockMethodCallHandler((MethodCall methodCall) async {
//      return '42';
//    });
//  });
//
//  tearDown(() {
//    channel.setMockMethodCallHandler(null);
//  });
//
//  test('getPlatformVersion', () async {
//    expect(await PayTabsFlutter.platformVersion, '42');
//  });
//}
