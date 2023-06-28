package com.example.audiobook.core.domain.util

internal fun convertToProgress(count : Long , total : Long) =
    ((count * 100f) / total / 100f).takeIf(Float::isFinite) ?: 0f