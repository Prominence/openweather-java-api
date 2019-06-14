/*
 * Copyright (c) 2019 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.model;

import java.util.Objects;

public class Pressure {

    private double value;

    private double seaLevelValue;
    private double groundLevelValue;

    public Pressure() {
    }

    public Pressure(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getSeaLevelValue() {
        return seaLevelValue;
    }

    public void setSeaLevelValue(double seaLevelValue) {
        this.seaLevelValue = seaLevelValue;
    }

    public double getGroundLevelValue() {
        return groundLevelValue;
    }

    public void setGroundLevelValue(double groundLevelValue) {
        this.groundLevelValue = groundLevelValue;
    }

    public String getUnit() {
        return "hPa";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pressure)) return false;
        Pressure pressure = (Pressure) o;
        return Double.compare(pressure.value, value) == 0 &&
                Double.compare(pressure.seaLevelValue, seaLevelValue) == 0 &&
                Double.compare(pressure.groundLevelValue, groundLevelValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, seaLevelValue, groundLevelValue);
    }

    @Override
    public String toString() {
        return "Pressure: " + value + ' ' + getUnit();
    }
}