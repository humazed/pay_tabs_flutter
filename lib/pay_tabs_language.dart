enum PayTabsLanguage { ar, en }

extension ParseToString on PayTabsLanguage {
  String toShortString() {
    return this.toString().split('.').last;
  }
}
