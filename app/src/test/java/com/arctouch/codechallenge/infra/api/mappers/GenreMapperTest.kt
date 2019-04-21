package com.arctouch.codechallenge.infra.api.mappers

import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.infra.api.models.GenreItem
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class GenreMapperTest {

    private lateinit var mapper: GenreMapper

    @Before
    fun setup() {
        mapper = GenreMapper()
    }

    @Test
    fun testMapping() {
        val item = GenreItem(
            id = 20,
            name = "Name"
        )
        val genre = mapper.map(item)
        Assert.assertEquals(genre.id, item.id)
        Assert.assertEquals(genre.name, item.name)
    }

    @Test
    fun testReverseMapping() {
        val item = Genre(
            id = 20,
            name = "Name"
        )
        val genre = mapper.mapReverse(item)
        Assert.assertEquals(genre.id, item.id)
        Assert.assertEquals(genre.name, item.name)
    }

}