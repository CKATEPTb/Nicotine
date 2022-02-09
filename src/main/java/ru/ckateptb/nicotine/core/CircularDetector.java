/*
 * Copyright (c) 2022 CKATEPTb <https://github.com/CKATEPTb>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ru.ckateptb.nicotine.core;

import ru.ckateptb.nicotine.exception.IoCCircularDepException;

import java.util.HashMap;
import java.util.Map;

public class CircularDetector {
    private final Map<Class<?>, Integer> circularDetectMap = new HashMap<>(30);

    public void detect(Class<?> clazz) throws IoCCircularDepException {
        int circular = circularDetectMap.getOrDefault(clazz, 0);
        circularDetectMap.put(clazz, circular + 1);
        // Need to be changed
        if (circular > 50) {
            throw new IoCCircularDepException("Circular dependency detected when loading class " + clazz.getName());
        }
    }
}