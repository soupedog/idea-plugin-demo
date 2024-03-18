package com.example.plugin.demo.component.toolWindow

import com.example.plugin.demo.component.toolWindow.service.MyToolWindowService
import com.example.plugin.demo.util.BundleUtil
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import javax.swing.JButton

/**
 * 新建一个工具栏(和 project 栏类似)，中间包含一个按钮，每次点击生成一个随机数并切换语言
 */
class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("MyToolWindowFactory start.")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyToolWindowService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val labelInitText: String = BundleUtil.message(service.getCurrentLanguage(), "randomLabel", "?")
            val label = JBLabel(labelInitText)

            val button = createButton(label)

            add(label)
            add(button)
        }

        fun createButton(label: JBLabel): JButton {
            val buttonText: String = BundleUtil.message(service.getCurrentLanguage(), "shuffle")
            val button = JButton(buttonText)

            // 点击事件监听
            button.addActionListener {
                // 切换语言
                service.setCurrentLanguage()

                // 重新加载文本
                label.text = BundleUtil.message(service.getCurrentLanguage(), "randomLabel", service.getRandomNumber())
                button.text = BundleUtil.message(service.getCurrentLanguage(), "shuffle")
            }
            return button
        }
    }
}
