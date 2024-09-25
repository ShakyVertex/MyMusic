## 项目亮点

* 使用fragment实现用户协议对话框，结合HTML组件生成协议动态链接并用PreferenceManager保存用户选择
* 引入PermissionsDispatcher组件，动态检查和获取软件所需要的外部存储读写权限和相机相册使用的用户权限
* 使用viewBinding替代findViewById来完成视图绑定，降低代码的复杂性并且有效的避免了空指针异常和类转换异常的风险；利用反射机制重写onCreate方法，在创建类的时候传入泛型来完成自动化视图绑定



## 任务日志

2024.9.25 出了BUG，不知道怎么调试，反正就是莫名的app出现故障，View无法正常跳转，fragment里的HTML替代也出现了问题，md每次我都很烦讲课的老师