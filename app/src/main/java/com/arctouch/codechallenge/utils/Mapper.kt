package com.arctouch.codechallenge.utils

/**
 * Created by Rafael Decker on 2019-04-19.
 */

interface Mapper<In, Out> {

    fun map(param: In): Out

    fun mapList(param: List<In>): List<Out> = param.map(::map)

    fun mapReverse(param: Out): In

    fun mapListReverse(param: List<Out>): List<In> = param.map(::mapReverse)

}
