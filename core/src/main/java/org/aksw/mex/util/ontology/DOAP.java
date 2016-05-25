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
 * Created by esteves on 28.06.15.
 */
public class DOAP {
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://usefulinc.com/ns/doap#";
    public static final Resource NAMESPACE;
    public static final Property name;
    public static final Property homepage;
    public static final Property revision;
    public static final Property description;
    public static final Property programming_language;
    public static final Property os;
    public static final Property category;
    public static final Property location;


    public DOAP() {
    }

    public static String getURI() {
        return "http://usefulinc.com/ns/doap#";
    }

    static {
        NAMESPACE = m_model.createResource("http://usefulinc.com/ns/doap#");
        name = m_model.createProperty("http://usefulinc.com/ns/doap#name");
        homepage = m_model.createProperty("http://usefulinc.com/ns/doap#homepage");
        revision = m_model.createProperty("http://usefulinc.com/ns/doap#revision");
        description = m_model.createProperty("http://usefulinc.com/ns/doap#description");
        programming_language = m_model.createProperty("http://usefulinc.com/ns/doap#programming-language");
        os = m_model.createProperty("http://usefulinc.com/ns/doap#os");
        category = m_model.createProperty("http://usefulinc.com/ns/doap#category");
        location = m_model.createProperty("http://usefulinc.com/ns/doap#location");

    }


}
