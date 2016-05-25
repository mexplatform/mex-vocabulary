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

package org.aksw.mex.log4mex;

/**
 * Created by dnes on 22/05/16.
 */
public abstract class InstanceObjects {

    String _individualName = "";
    String _label = "";

    public String getIndividualName(){
        return this._individualName;
    }

    public void setIndividualName(String value){
        this._individualName = value;
    }

    public String getLabel(){
        return this._label;
    }

    public void setLabel(String value){
        this._label = value;
    }


    public abstract boolean equals(Object other);

    public abstract int hashCode();

}
