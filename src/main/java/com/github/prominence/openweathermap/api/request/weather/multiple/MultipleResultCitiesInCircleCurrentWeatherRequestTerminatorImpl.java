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

package com.github.prominence.openweathermap.api.request.weather.multiple;

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.github.prominence.openweathermap.api.request.RequestUrlBuilder;
import com.github.prominence.openweathermap.api.request.weather.CurrentWeatherResponseMapper;
import com.github.prominence.openweathermap.api.utils.RequestUtils;

import java.util.List;

/**
 * The type Multiple result current weather request terminator.
 */
public class MultipleResultCitiesInCircleCurrentWeatherRequestTerminatorImpl implements MultipleResultCitiesInCircleCurrentWeatherRequestTerminator {
    private final RequestUrlBuilder urlBuilder;
    private final UnitSystem unitSystem;

    /**
     * Instantiates a new Multiple result current weather request terminator.
     *
     * @param urlBuilder the url builder
     * @param unitSystem the unit system
     */
    MultipleResultCitiesInCircleCurrentWeatherRequestTerminatorImpl(RequestUrlBuilder urlBuilder, UnitSystem unitSystem) {
        this.urlBuilder = urlBuilder;
        this.unitSystem = unitSystem;
    }

    @Override
    public List<Weather> asJava() {
        return new CurrentWeatherResponseMapper(unitSystem).getList(getRawResponse());
    }

    @Override
    public String asJSON() {
        return getRawResponse();
    }

    @Override
    public String asXML() {
        urlBuilder.addRequestParameter("mode", "xml");
        return getRawResponse();
    }

    private String getRawResponse() {
        return RequestUtils.getResponse(urlBuilder.buildUrl());
    }
}
