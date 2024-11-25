package com.example.otchallenge.common.ui

import org.amshove.kluent.`should be equal to`
import org.junit.Test

class BaseDiffCallbackTest {

    private val tested = BaseDiffCallback<TestDto> { id }

    @Test
    fun `given items have same ids, when areItemsTheSame called, return true`() {
        // given
        val item1 = TestDto("id", 1)
        val item2 = TestDto("id", 2)

        // when
        val result = tested.areItemsTheSame(item1, item2)

        // then
        result `should be equal to` true
    }

    @Test
    fun `given items have different ids, when areItemsTheSame called, return false`() {
        // given
        val item1 = TestDto("id", 1)
        val item2 = TestDto("id1", 1)

        // when
        val result = tested.areItemsTheSame(item1, item2)

        // then
        result `should be equal to` false
    }

    @Test
    fun `given items equal, when areContentsTheSame called, return true`() {
        // given
        val item1 = TestDto("id", 1)
        val item2 = TestDto("id", 1)

        // when
        val result = tested.areContentsTheSame(item1, item2)

        // then
        result `should be equal to` true
    }

    @Test
    fun `given items not equal, when areContentsTheSame called, return false`() {
        // given
        val item1 = TestDto("id", 1)
        val item2 = TestDto("id", 2)

        // when
        val result = tested.areContentsTheSame(item1, item2)

        // then
        result `should be equal to` false
    }
}

private data class TestDto(val id: String, val otherProperty: Int)
