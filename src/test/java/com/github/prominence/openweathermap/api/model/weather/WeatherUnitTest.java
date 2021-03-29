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

package com.github.prominence.openweathermap.api.model.weather;

import com.github.prominence.openweathermap.api.model.AtmosphericPressure;
import com.github.prominence.openweathermap.api.model.Clouds;
import com.github.prominence.openweathermap.api.model.Humidity;
import com.github.prominence.openweathermap.api.model.Temperature;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class WeatherUnitTest {
    @Test
    public void whenSetRequestedOn_thenValueIsSet() {
        final Weather weather = new Weather();
        final LocalDateTime now = LocalDateTime.now();
        weather.setCalculationTime(now);

        Assert.assertEquals(now, weather.getCalculationTime());
    }

    @Test
    public void whenSetTemperature_thenValueIsSet() {
        final Weather weather = new Weather();
        final Temperature temperature = Temperature.withValue(22.3, "a");
        weather.setTemperature(temperature);

        Assert.assertEquals(temperature, weather.getTemperature());
    }

    @Test
    public void whenSetPressure_thenValueIsSet() {
        final Weather weather = new Weather();
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.2);
        weather.setAtmosphericPressure(atmosphericPressure);

        Assert.assertEquals(atmosphericPressure, weather.getAtmosphericPressure());
    }

    @Test
    public void whenSetHumidity_thenValueIsSet() {
        final Weather weather = new Weather();
        final Humidity humidity = Humidity.withValue((byte) 44);
        weather.setHumidity(humidity);

        Assert.assertEquals(humidity, weather.getHumidity());
    }

    @Test
    public void whenSetWind_thenValueIsSet() {
        final Weather weather = new Weather();
        final Wind wind = Wind.withValue(22.2, "a");
        weather.setWind(wind);

        Assert.assertEquals(wind, weather.getWind());
    }

    @Test
    public void whenSetRain_thenValueIsSet() {
        final Weather weather = new Weather();
        final Rain rain = Rain.withValues(0, 0);
        weather.setRain(rain);

        Assert.assertEquals(rain, weather.getRain());
    }

    @Test
    public void whenSetSnow_thenValueIsSet() {
        final Weather weather = new Weather();
        final Snow snow = Snow.withValues(0, 0);
        weather.setSnow(snow);

        Assert.assertEquals(snow, weather.getSnow());
    }

    @Test
    public void whenSetClouds_thenValueIsSet() {
        final Weather weather = new Weather();
        final Clouds clouds = Clouds.withValue((byte) 33);
        weather.setClouds(clouds);

        Assert.assertEquals(clouds, weather.getClouds());
    }

    @Test
    public void whenSetLocation_thenValueIsSet() {
        final Weather weather = new Weather();
        final Location location = Location.withValues(22, "asd");
        weather.setLocation(location);

        Assert.assertEquals(location, weather.getLocation());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Weather weather = new Weather();
        final Location location = Location.withValues(12312, "asd");
        final Temperature temperature = Temperature.withValue(33.2, "asd");
        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(44.4);
        final Clouds clouds = Clouds.withValue((byte) 55);
        final Rain rain = Rain.withOneHourLevelValue(33.2);
        final Snow snow = Snow.withOneHourLevelValue(33.1);

        Assert.assertEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        weather.setLocation(location);
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        location.setCountryCode("3d");
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        weather.setTemperature(temperature);
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        weather.setAtmosphericPressure(atmosphericPressure);
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        weather.setClouds(clouds);
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        weather.setRain(rain);
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());

        weather.setSnow(snow);
        Assert.assertNotEquals("", weather.toString());
        Assert.assertNotNull(weather.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Weather one = new Weather();
        final Weather two = new Weather();

        Assert.assertEquals(one.hashCode(), two.hashCode());

        two.setCalculationTime(LocalDateTime.now());

        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Weather one = new Weather();
        final Weather two = new Weather();

        Assert.assertTrue(one.equals(one));
        Assert.assertFalse(one.equals(new Object()));
        Assert.assertTrue(one.equals(two));

        final LocalDateTime now = LocalDateTime.now();
        one.setCalculationTime(now);

        Assert.assertFalse(one.equals(two));

        two.setCalculationTime(now);

        Assert.assertTrue(one.equals(two));

        final Temperature temperature = Temperature.withValue(33.2, "as");
        one.setTemperature(temperature);

        Assert.assertFalse(one.equals(two));

        two.setTemperature(temperature);

        Assert.assertTrue(one.equals(two));

        final AtmosphericPressure atmosphericPressure = AtmosphericPressure.withValue(33.33);
        one.setAtmosphericPressure(atmosphericPressure);

        Assert.assertFalse(one.equals(two));

        two.setAtmosphericPressure(atmosphericPressure);

        Assert.assertTrue(one.equals(two));

        final Humidity humidity = Humidity.withValue((byte) 33);
        one.setHumidity(humidity);

        Assert.assertFalse(one.equals(two));

        two.setHumidity(humidity);

        Assert.assertTrue(one.equals(two));

        final Wind wind = Wind.withValue(33.6, "asd");
        one.setWind(wind);

        Assert.assertFalse(one.equals(two));

        two.setWind(wind);

        Assert.assertTrue(one.equals(two));

        final Rain rain = Rain.withValues(0, 0);
        one.setRain(rain);

        Assert.assertFalse(one.equals(two));

        two.setRain(rain);

        Assert.assertTrue(one.equals(two));

        final Snow snow = Snow.withValues(0, 0);
        one.setSnow(snow);

        Assert.assertFalse(one.equals(two));

        two.setSnow(snow);

        Assert.assertTrue(one.equals(two));

        final Clouds clouds = Clouds.withValue((byte) 33);
        one.setClouds(clouds);

        Assert.assertFalse(one.equals(two));

        two.setClouds(clouds);

        Assert.assertTrue(one.equals(two));

        final Location location = Location.withValues(231, "asda");
        one.setLocation(location);

        Assert.assertFalse(one.equals(two));

        two.setLocation(location);

        Assert.assertTrue(one.equals(two));
    }
}
