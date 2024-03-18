package com.example.plugin.demo.component.toolWindow.service

import com.example.plugin.demo.language.LanguageEnum
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.example.plugin.demo.language.EnBundle

@Service(Service.Level.PROJECT)
class MyToolWindowService(project: Project) {
    private var currentLanguage: LanguageEnum = LanguageEnum.EN;

    init {
        thisLogger().info(EnBundle.message("projectService", project.name))
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    fun getRandomNumber() = (1..100).random()

    fun setCurrentLanguage(language: LanguageEnum? = null) {
        if (language == null) {
            if (LanguageEnum.EN == currentLanguage) {
                currentLanguage = LanguageEnum.ZH
            } else {
                currentLanguage = LanguageEnum.EN
            }
        } else {
            currentLanguage = language
        }
    }

    fun getCurrentLanguage(): LanguageEnum {
        return currentLanguage
    }
}
