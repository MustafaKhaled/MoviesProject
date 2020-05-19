package com.werd.khaleds.moviesprojectswvlchallenge.data.repo
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException
import java.lang.reflect.Type


class MoviesParsableImplTest {
    lateinit var  file: String
    lateinit var ctx:Context
    @Before
    fun setup(){
        ctx = getInstrumentation().targetContext
        file = ctx.resources.assets.open("movies.json").bufferedReader().use{
            it.readText()}
    }

    @Test(expected = FileNotFoundException::class)
    fun throw_exception_file_not_found() {
        file = ctx.resources.assets.open("invlidfile.json").bufferedReader().use{
            it.readText()}
    }
    @Test
    fun check_json_file_is_read_success(){
        val gson = Gson()
        val listType: Type = object : TypeToken<MoviesLocalResult>() {}.type
        val parse  = gson.fromJson<MoviesLocalResult>(file,listType)
        assertEquals(parse.movies.size, 2272)
    }
}