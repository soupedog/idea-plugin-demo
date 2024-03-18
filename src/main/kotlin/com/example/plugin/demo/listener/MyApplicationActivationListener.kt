package com.example.plugin.demo.listener

import com.intellij.openapi.application.ApplicationActivationListener
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.wm.IdeFrame

/**
 * 注册完监听后，当 idea 应用窗口在操作系统(如 Windows)从未获得焦点变化为得到焦点时触发 applicationActivated() 中的逻辑
 */
internal class MyApplicationActivationListener : ApplicationActivationListener {

    override fun applicationActivated(ideFrame: IdeFrame) {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }
}
