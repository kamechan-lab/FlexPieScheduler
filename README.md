# FlexPieScheduler
概要はwikiにあります。

・アプリの機能部分（Utilityクラス含む）→FlexPieLib  
デザイン部分→res 
に保存してください。

・良く使うSectorクラスはFlexPieLib/componentsフォルダにあります。

・実機テストする際にandroidのAPKが26未満の人はjava.timeライブラリではなく、互換性のあるorg.threeten.bpライブラリをmavenからダウンロードして使って下さい。  
org.threeten.bpライブラリをインクルードするために更新したgradleをアップロードしておきます。
