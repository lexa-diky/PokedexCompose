package com.skosc.pokedex.domain.settings

import androidx.datastore.core.Serializer
import com.google.protobuf.AbstractMessageLite
import com.skosc.pokedex.core.localization.Localization
import java.io.InputStream
import java.io.OutputStream

object AppSettingsSerializer : Serializer<AppSettingsProtobuf> {

    override val defaultValue: AppSettingsProtobuf = AppSettingsProtobuf.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppSettingsProtobuf {
        return AppSettingsProtobuf.parseFrom(input)
    }

    override suspend fun writeTo(t: AppSettingsProtobuf, output: OutputStream) {
        t.writeTo(output)
    }
}