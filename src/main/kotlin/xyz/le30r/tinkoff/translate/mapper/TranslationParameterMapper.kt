package xyz.le30r.tinkoff.translate.mapper

import xyz.le30r.tinkoff.translate.dto.TranslationParamsDto
import xyz.le30r.tinkoff.translate.model.TranslationParameter


private const val TARGET_LANGUAGE_PARAM_NAME = "targetLang"
private const val SOURCE_LANGUAGE_PARAM_NAME = "sourceLang"

class TranslationParameterMapper : EntityMapper<TranslationParamsDto, List<TranslationParameter>> {
    override fun map(source: TranslationParamsDto): List<TranslationParameter> {
        val result = mutableListOf<TranslationParameter>()
        result.add(TranslationParameter(0, TARGET_LANGUAGE_PARAM_NAME, source.targetLang))
        source.sourceLang?.let {
            result.add(TranslationParameter(0, SOURCE_LANGUAGE_PARAM_NAME, it))
        }
        return result
    }

}