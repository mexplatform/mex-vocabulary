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

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.log4mex.core.ApplicationContextVO;
import org.aksw.mex.log4mex.core.ExperimentVO;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXController;
import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by esteves on 26.06.15.
 */
public class MyMEX {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMEX.class);

    private ApplicationContextVO applicationContext;
    private ExperimentVO experiment;
    private List<ExperimentConfigurationVO> experimentConfigurationList;
    private boolean withoutConfiguration = false;
    private String userHash;

    public MyMEX(){
        this.applicationContext = new ApplicationContextVO();
        this.experiment = new ExperimentVO(MEXConstant.DEFAULT_EXP_ID);
        this.experimentConfigurationList = new ArrayList<>();
    }

    /* mexcore:ApplicationContext */

    public ApplicationContextVO getApplicationContext() {return applicationContext;}

    public void setAuthor(String name, String email){
        this.applicationContext.setAuthorName(name);
        this.applicationContext.setMailBox(email);
    }

    public void setAuthorName(String value){this.applicationContext.setAuthorName(value);}

    public void setAuthorEmail(String value){this.applicationContext.setMailBox(value);}

    public void setContext(MEXEnum.EnumContexts value){this.applicationContext.setContext(value);}

    public void setOrganization(String value){this.applicationContext.setOrganization(value);}

    /* mexcore:Experiment */

    public ExperimentVO getExperiment() {return experiment;}

    public void setExperimentId(String value){this.experiment.setId(value);}

    public void setExperimentTitle(String value){this.experiment.setTitle(value);}

    public void setExperimentDate(Date value){this.experiment.setDate(value);}

    public void setExperimentDescription(String value){this.experiment.setDescription(value);}

    public void setExperimentDataNormalizationDescription(String value){this.experiment.setDataNormalizationDescription(value);}

    public void setExperimentNoiseRemovalDescription(String value){this.experiment.setNoiseRemovalDescription(value);}

    public void setExperimentAttributeSelectionDescription(String value){this.experiment.setAttributeSelectionDescription(value);}

    public void setExperimentOutlierDetectionDescription(String value){this.experiment.setOutlierDetectionDescription(value);}

    /* mexcore:ExperimentConfiguration */

    private String addConf(String value) throws Exception {
        String ret="";
        String valueaux="";
        String logmessage;

        try {

            if (withoutConfiguration == true && (StringUtils.isNotEmpty(value) || StringUtils.isNotBlank(value)) ){
                logmessage = "You defined a SINGLE Configuration before and now are trying add a MULTIPLE Configuration. It's not allowed, sorry!"
                        + " Please change your method calls in order to have either [Configuration()] calls (just ONE configuration) or [addConfiguration(id) and Configuration(id)] (MORE THAN ONE configuration)";
                LOGGER.warn(logmessage);
                throw new Exception(logmessage);
            } else if (this.experimentConfigurationList == null){
                logmessage = "fatal error: experiment config list is null!";
                LOGGER.warn(logmessage);
                throw new Exception(logmessage);
            }

            int totalElements = this.experimentConfigurationList.size();

            //automatic incrementation process
            if (StringUtils.isEmpty(value) || StringUtils.isBlank(value)) {
                /*
                Integer nextID = 0;
                Collection<ExperimentConfigurationVO> tdefault = Collections2.filter(
                        this.experimentConfigurationList, p -> p.getId().contains(MEXConstant.DEFAULT_EXP_CONFIGURATION_ID));
                if (tdefault != null) {
                    nextID = tdefault.size() + 1;
                }
                 */
                valueaux = MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + String.valueOf(totalElements + 1);
            }
            else //manual incrementation process
            {
                valueaux = value;
            }

            //checking the existing
            final String check = valueaux;
            Collection<ExperimentConfigurationVO> t = Collections2.filter(this.experimentConfigurationList, p -> p.getId().equals(check));
            if (t != null && t.size() > 0){
                throw new Exception("Error: Experiment Configuration ID " + check + " already assigned");}
            //adding the code
            if (this.experimentConfigurationList.add(new ExperimentConfigurationVO(valueaux, totalElements + 1))==false){
                throw new Exception("Error when including the item in the list");}

        }catch (Exception e){
            LOGGER.error(e.toString());
        }
        return valueaux;
    }

    public String addConfiguration() throws Exception{

        String ret = "";

        try {

            ret = addConf(StringUtils.EMPTY);

            MEXController.getInstance().addExperimentConfigurationCounter();

        } catch (Exception e){

            LOGGER.error(e.toString());
            throw(e);
        }

        return ret;
    }

    public String addConfiguration(String value) throws Exception{

        String ret = "";

        try {

            ret = addConf(value);

        }catch (Exception e){

            LOGGER.error(e.toString());
            throw(e);

        }

        return ret;

    }

    protected List<ExperimentConfigurationVO> getExperimentConfigurations() {return this.experimentConfigurationList;}

    public ExperimentConfigurationVO Configuration(String value) throws Exception{

        ExperimentConfigurationVO ret = null;
        try{
            Collection<ExperimentConfigurationVO> t
                    = Collections2.filter(this.experimentConfigurationList, experimentConfigurationVO -> experimentConfigurationVO.getId().equals(value));
            if (t != null && t.size() >0){
                ret =  Iterables.get(t, 0);
            }else {
                throw new Exception("Configuration ID has not been found: " + value);
            }
        }catch (Exception e){
            LOGGER.error(e.toString());
        }

        return ret;

    }

    public ExperimentConfigurationVO Configuration() throws Exception{

        ExperimentConfigurationVO ret = null;

        try{

            final String id = MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + "1";
            String logmsg;

            //bug 1
            if (this.experimentConfigurationList == null) {
                logmsg = "We got a problem accessing the configurations (rule 1)! It looks like a bug! Please report it!";
                LOGGER.warn(logmsg);
                throw new Exception(logmsg);
            }
            else if (withoutConfiguration == false && this.experimentConfigurationList.size() > 0){
                logmsg = "You defined a MULTIPLE Configuration (by calling addConfiguration(id) method) before and now are trying add a SINGLE Configuration (by calling Configuration() method). It's not allowed, sorry!";
                LOGGER.warn(logmsg);
                throw new Exception(logmsg);
            }
            //bug2
            else if (withoutConfiguration == true && this.experimentConfigurationList.size() == 0){
                logmsg = "We got a problem accessing the configurations (rule 2)! It looks like a bug! Please report it!";
                LOGGER.warn(logmsg);
                throw new Exception(logmsg);
            }
            //first access to Configuration()
            else if (withoutConfiguration == false && this.experimentConfigurationList.size() == 0){
                withoutConfiguration = true;
                ExperimentConfigurationVO conf = new ExperimentConfigurationVO(id, 1);
                this.experimentConfigurationList.add(conf);
                ret = conf;
            }
            //recurrent access to Configuration()
            else if (withoutConfiguration == true && this.experimentConfigurationList.size() == 1){
                ret = this.experimentConfigurationList.get(0);
            }
            else {
                logmsg = "We got a problem accessing the configurations (rule 3)! It looks like a bug! Please report it!";
                LOGGER.warn(logmsg);
                throw new Exception(logmsg);
            }

        }catch (Exception e){
            LOGGER.error(e.toString());
        }

        return ret;

    }

    protected void setUserHash() throws Exception{
        try{
            Random rand = new Random();
            int  n = rand.nextInt(31999) + 1;
            this.userHash = String.valueOf(String.valueOf((getApplicationContext().get_mbox()) +
                    String.valueOf(getApplicationContext().get_givenName()) + getExperiment().getId() + n).hashCode());
        }catch (Exception e){
            throw (e);
        }
    }

    protected String getUserHash(){
        return this.userHash;
    }
}