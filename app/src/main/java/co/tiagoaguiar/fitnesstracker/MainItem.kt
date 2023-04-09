package co.tiagoaguiar.fitnesstracker

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItem (
    //todos arquivos de recursos sao trabalhados como inteiros
    //botar isto na frente de uma variavel  para informar que este tipo de inteiro é apenas um arquivo de recurso
    val id: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val textStringId: Int,
    val color: Int
)