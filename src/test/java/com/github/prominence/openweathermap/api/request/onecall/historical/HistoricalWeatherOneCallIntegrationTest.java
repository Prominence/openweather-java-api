/*
 * Copyright (c) 2021 Alexey Zinchenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.prominence.openweathermap.api.request.onecall.historical;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HistoricalWeatherOneCallIntegrationTest extends ApiTest {
    @Test
    public void whenRetrieveHistoricalOneCallResponseAsJava_thenOk() {
        final HistoricalWeatherData historicalWeatherData = getClient()
                .oneCall()
                .historical()
                .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        Assert.assertNotNull(historicalWeatherData);
    }

    @Test
    public void whenRetrieveHistoricalOneCallResponseAsJSON_thenOk() {
        final String responseJson = getClient()
                .oneCall()
                .historical()
                .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

        Assert.assertNotNull(responseJson);
        Assert.assertNotEquals("", responseJson);
        System.out.println(responseJson);
    }

    @Test
    public void whenRetrieveHistoricalOneCallAsyncResponseAsJava_thenOk() throws ExecutionException, InterruptedException {
        final CompletableFuture<HistoricalWeatherData> historicalWeatherDataFuture = getClient()
                .oneCall()
                .historical()
                .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJava();

        Assert.assertNotNull(historicalWeatherDataFuture);
        Assert.assertNotNull(historicalWeatherDataFuture.get());
    }

    @Test
    public void whenRetrieveHistoricalOneCallAsyncResponseAsJSON_thenOk() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> responseJsonFuture = getClient()
                .oneCall()
                .historical()
                .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asJSON();

        Assert.assertNotNull(responseJsonFuture);
        final String responseJson = responseJsonFuture.get();
        Assert.assertNotEquals("", responseJson);
        System.out.println(responseJson);
    }
}
