package io.github.nyliumpowered.nylium.util

import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@Target(CLASS, FUNCTION, PROPERTY, CONSTRUCTOR, FIELD, ANNOTATION_CLASS)
@Retention(SOURCE)
annotation class InternalUseOnly