/**
 * Copyright (C) 2014 - 2016, Diego Esteves
 *
 * This file is part of LOG4MEX.
 *
 * LOG4MEX is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * LOG4MEX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.aksw.mex.util;

/**
 * Created by esteves on 03.07.15.
 */
public class MEXController {
    private int _numberOfExperimentConfigurations;
    private int _numberOfSamplingMethods;
    private int _numberOfAlgorithms;


    private static MEXController instance = null;
    protected MEXController() {
        _numberOfExperimentConfigurations = 0;
        _numberOfSamplingMethods=0;
        _numberOfAlgorithms=0;
    }
    public static MEXController getInstance() {
        if(instance == null) {
            instance = new MEXController();
        }
        return instance;
    }

    public int getNumberOfExperimentConfigurations(){
        return this._numberOfExperimentConfigurations;
    }
    public void addExperimentConfigurationCounter(){
        this._numberOfExperimentConfigurations ++;
    }

    public int getNumberOfSamplingMethods(){
        return this._numberOfSamplingMethods;
    }
    public void addSamplingMethodCounter(){
        this._numberOfSamplingMethods ++;
    }

    public int getNumberOfAlgorithms(){
        return this._numberOfAlgorithms;
    }
    public void addAlgorithmCounter(){
        this._numberOfAlgorithms ++;
    }

}
