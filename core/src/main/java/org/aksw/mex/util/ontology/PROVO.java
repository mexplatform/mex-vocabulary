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
package org.aksw.mex.util.ontology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Created by esteves on 27.06.15.
 */
public class PROVO implements IOntology {

    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://www.w3.org/ns/prov#";
    public static final String PREFIX = "prov";
    public static final Resource NAMESPACE;

    public static final Property wasAttributedTo;
    public static final Property used;
    public static final Property wasDerivedFrom;
    public static final Property hadMember;
    public static final Property wasStartedBy;
    public static final Property wasInformedBy;
    public static final Property wasGeneratedBy;
    public static final Property generated;
    public static final Property actedOnBehalfOf;

    public static final Property id;
    public static final Property value;
    public static final Property startedAtTime;
    public static final Property endedAtTime;

    protected PROVO() {
    }



    static {
        NAMESPACE = m_model.createResource(NS);
        wasAttributedTo = m_model.createProperty(NS + "wasAttributedTo");
        used = m_model.createProperty(NS + "used");
        wasDerivedFrom = m_model.createProperty(NS + "wasDerivedFrom");
        hadMember = m_model.createProperty(NS + "hadMember");
        wasStartedBy = m_model.createProperty(NS + "wasStartedBy");
        wasInformedBy = m_model.createProperty(NS + "wasInformedBy");
        wasGeneratedBy = m_model.createProperty(NS + "wasGeneratedBy");
        generated = m_model.createProperty(NS + "generated");
        actedOnBehalfOf = m_model.createProperty(NS + "actedOnBehalfOf");

        id = m_model.createProperty(NS + "id");
        value = m_model.createProperty(NS + "value");
        startedAtTime = m_model.createProperty(NS + "startedAtTime");
        endedAtTime = m_model.createProperty(NS + "endedAtTime");
    }


    public class ClasseTypes {
        public static final String AGENT = "Agent";
        public static final String ENTITY = "Entity";
        public static final String PERSON="Person";
        public static final String ORGANIZATION="Organization";
        public static final String ACTIVITY="Activity";
        public static final String COLLECTION="Collection";
    }


}
