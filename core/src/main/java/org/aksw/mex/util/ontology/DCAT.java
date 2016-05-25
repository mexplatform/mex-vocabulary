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
public class DCAT {
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://www.w3.org/ns/dcat#";
    public static final Resource NAMESPACE;
    public static final Property landingPage;


    public DCAT() {
    }

    public static String getURI() {
        return "http://www.w3.org/ns/dcat#";
    }

    static {
        NAMESPACE = m_model.createResource("http://www.w3.org/ns/dcat#");
        landingPage = m_model.createProperty("http://www.w3.org/ns/dcat#landingPage");

    }


}
