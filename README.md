# Native Library Plugin

提供 Android 端 Minecraft Java 版启动器识别并加载的原生依赖库插件。  
注：这是一个模板仓库，您可以在 [build.gradle.kts](./app/build.gradle.kts) 文件中配置这个插件

## 配置细节
- `app_name`
  - 软件名称：这个插件并未被设计为无桌面图标的应用，而是能显示在桌面，被用户自由删除的应用，因此需要配置软件名
- `des`
  - 插件描述：被启动器识别后，将在插件列表中显示的描述，留空或删除这个配置，启动器将不会显示描述
- `environment`
  - JVM 环境参数配置：将自定义的参数设置插入到启动游戏时的 JVM 环境参数中
    ```kotlin
    //配置示例
    put("example.plugin", "example")
    //替换符{nativeLibraryDir}：将替换为该插件的原生库路径
    //注：{nativeLibraryDir}只能作为开头部分，无需在中间插入"/"，启动器将自动拼接路径
    put("example.plugin.extra", "{nativeLibraryDir}libexample.so")
    //最终启动器将插入
    "-Dexample.plugin=example -Dexample.plugin.extra=xxx/xxx/libexample.so"
    ```
- `minMCVer`
  - 最小 MC 支持版本：配置该插件最小支持的 Minecraft 版本，留空或删除这个配置，启动器将视为不限制最小版本
- `maxMCVer`
  - 最大 MC 支持版本：配置该插件最大支持的 Minecraft 版本，留空或删除这个配置，启动器将视为不限制最大版本
  - 如果同时留空或删除 `minMCVer`、`maxMCVer`，启动器将视为支持全版本
