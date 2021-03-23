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

import org.junit.Assert;
import org.junit.Test;

public class WindUnitTest {
    @Test
    public void whenCreateWindWithValidArgs_thenValueIsSet() {
        Assert.assertEquals(44.0, Wind.forValue(44, "ms").getSpeed(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWindWithInvalidSpeedArg_thenThrowAnException() {
        Wind.forValue(-21, "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWindWithInvalidUnitArg_thenThrowAnException() {
        Wind.forValue(342, null);
    }

    @Test
    public void whenSetValidSpeed_thenValueIsSet() {
        final Wind wind = Wind.forValue(33, "as");

        Assert.assertEquals(33, wind.getSpeed(), 0.00001);

        wind.setSpeed(0);

        Assert.assertEquals(0, wind.getSpeed(), 0.00001);

        wind.setSpeed(3656);

        Assert.assertEquals(3656, wind.getSpeed(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidSpeedBelow0_thenThrowAnException() {
        final Wind wind = Wind.forValue(33, "as");

        Assert.assertEquals(33, wind.getSpeed(), 0.00001);

        wind.setSpeed(-22);
    }

    @Test
    public void whenSetValidDegrees_thenValueIsSet() {
        final Wind wind = Wind.forValue(33, "as");

        Assert.assertNull(wind.getDegrees());

        wind.setDegrees(22);

        Assert.assertEquals(22, wind.getDegrees(), 0.00001);

        wind.setDegrees(0);

        Assert.assertEquals(0, wind.getDegrees(), 0.00001);

        wind.setDegrees(360);

        Assert.assertEquals(360, wind.getDegrees(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidDegreesBelow0_thenThrowAnException() {
        final Wind wind = Wind.forValue(33, "as");
        wind.setDegrees(-32);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidDegreesAbove360_thenThrowAnException() {
        final Wind wind = Wind.forValue(33, "as");
        wind.setDegrees(378);
    }

    @Test
    public void whenSetNonNullUnit_thenValueIsSet() {
        final Wind wind = Wind.forValue(33, "as");

        Assert.assertEquals(wind.getUnit(), "as");

        wind.setUnit("myUnit");

        Assert.assertEquals(wind.getUnit(), "myUnit");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetNullUnit_thenThrowAnException() {
        final Wind wind = Wind.forValue(33, "as");

        wind.setUnit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidGustValue_thenThrowAnException() {
        final Wind wind = Wind.forValue(33, "as");

        wind.setGust(-50);
    }

    @Test
    public void whenSetValidGustValue_thenAllIsFine() {
        final Wind wind = Wind.forValue(33, "as");

        wind.setGust(30);

        Assert.assertEquals(30, wind.getGust(), 0.00001);
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Wind wind = Wind.forValue(302, "a");

        Assert.assertNotNull(wind.toString());

        wind.setDegrees(22);

        Assert.assertNotNull(wind.toString());
        Assert.assertNotEquals("", wind.toString());

        wind.setGust(20);
        Assert.assertNotNull(wind.toString());
        Assert.assertNotEquals("", wind.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Wind first = Wind.forValue(22, "a");
        final Wind second = Wind.forValue(22, "b");

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        second.setUnit("a");

        Assert.assertEquals(first.hashCode(), second.hashCode());

        second.setSpeed(33);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        first.setSpeed(333);

        Assert.assertNotEquals(first.hashCode(), second.hashCode());

        first.setSpeed(33);

        Assert.assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Wind first = Wind.forValue(11, "a");
        final Wind second = Wind.forValue(11, "a");

        Assert.assertTrue(first.equals(second));
        Assert.assertTrue(first.equals(first));
        Assert.assertFalse(first.equals(new Object()));

        first.setDegrees(34);

        Assert.assertFalse(first.equals(second));

        second.setDegrees(34);

        Assert.assertTrue(first.equals(second));

        second.setUnit("v");

        Assert.assertFalse(first.equals(second));

        first.setUnit("v");

        Assert.assertTrue(first.equals(second));

        first.setGust(4);

        Assert.assertFalse(first.equals(second));

        second.setGust(4);

        Assert.assertTrue(first.equals(second));
    }
}