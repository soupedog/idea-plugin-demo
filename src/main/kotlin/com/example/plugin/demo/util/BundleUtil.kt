package com.example.plugin.demo.util

import com.example.plugin.demo.language.LanguageEnum
import com.intellij.DynamicBundle
import org.jetbrains.annotations.Nls
import com.example.plugin.demo.language.EnBundle
import com.example.plugin.demo.language.ZhBundle

object BundleUtil {

    fun message(language: LanguageEnum, key: String, vararg params: Any): @Nls String {
        val bundle = getBundle(language)
        return bundle.getMessage(key, *params)
    }

    private fun getBundle(language: LanguageEnum): DynamicBundle {
        return when (language) {
            LanguageEnum.EN -> EnBundle
            LanguageEnum.ZH -> ZhBundle
        }
    }
}