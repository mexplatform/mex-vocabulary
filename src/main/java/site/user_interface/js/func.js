var mexGen = angular.module('mexGen', ['ui.bootstrap', 'ngSanitize']);

function genController($scope, $modal, $http) {
    dt = new Date();
    dt = (dt.getMonth() + 1) + '-' + dt.getDate() + '-' + dt.getFullYear();


    $scope.messages = new Object();
    $scope.messages.notValidURI = "Not a valid URI!";
    $scope.showValidation = false;

    $scope.license = [
        {name: 'Creative Commons Attribution 4.0 International', val: 'http://purl.oclc.org/NET/rdflicense/cc-by'},
        {name: 'Creative Commons Attribution-ShareAlike 4.0 International', val: 'http://purl.oclc.org/NET/rdflicense/cc-by-sa'},
        {name: 'Creative Commons Attribution-NoDerivatives 4.0 International', val: 'http://purl.oclc.org/NET/rdflicense/cc-by-nd'},
        {name: 'Creative Commons Attribution-NonCommercial 4.0 International', val: 'http://purl.oclc.org/NET/rdflicense/cc-by-nc'},
        {name: 'Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International', val: 'http://purl.oclc.org/NET/rdflicense/cc-by-nc-nd'},
        {name: 'Creative Commons Public Domain Dedication', val: 'http://purl.oclc.org/NET/rdflicense/cc-zero'},
        {name: 'Open Data Commons Attribution License', val: 'http://purl.oclc.org/NET/rdflicense/odc-by'},
        {name: 'Open Data Commons Public Domain Dedication and License', val: 'http://purl.oclc.org/NET/rdflicense/odc-pddl'},
        {name: 'GNU General Public License', val: 'http://purl.oclc.org/NET/rdflicense/gpl-3.0'},
    ],
	

            emptyDataSet = {
                parentDataset: [],
                datasetURI: 'example',
                type: 'main',
                main: true,
                title: 'New subset',
                label: '',
                description: '',
                keyword: '',
                issued: dt,
                rights: '',
                rootResource: '',
                exampleResource: '',
                language: '',
                licenseName: '',
                landingPage: '',
                ontologyLocation: '',
                versionInfo: '',
                distribution: [],
                linkset: [],
                agent: [],
				active: [],
				feature: [],
				parameter:[],
				expe: [],
				expeconf: [],
                datasets: [],
                wasDerivedFromTitle: '',
                wasDerivedFromResource: '',
                wasGeneratedByTitle: '',
                wasGeneratedByResource: '',
                license: [],
				phase: [],
				perfomance: [],
				algo: [],
            }


    $scope.object = new Object();
    $scope.object.dataSets = [];
    $scope.object.format = 'turtle';

    $scope.object.dataSets.ds = [];

    var copiedDataset = {};
    var copiedDatasetFinal = {};
    jQuery.extend(copiedDataset, emptyDataSet);
    copiedDataset.title = 'Main Dataset';
    $scope.object.dataSets.push(copiedDataset);
    $scope.ds = copiedDataset;


    var i = 0;
    $scope.addDataset2 = function(dataset) {
        var copiedDataset = {};
        jQuery.extend(copiedDataset, emptyDataSet);
        copiedDataset.main = false;
        copiedDataset.parentDataset = dataset;
        copiedDataset.title = copiedDataset.title + i;
        copiedDataset.distribution = [];
        copiedDataset.linkset = [];
        copiedDataset.type = 'void:subset';
        copiedDataset.agent = [];
        copiedDataset.datasets = [];
        i++;
        dataset.datasets.push(copiedDataset);
        console.log(dataset.datasets);
    };

    $scope.delDataset2 = function(dataset) {
        var index = dataset.parentDataset.datasets.indexOf(dataset);
        dataset.parentDataset.datasets.splice(index, 1);
    }
    $scope.removeAllDist = function() {
        $scope.ds.distribution = [];
    }


    $scope.changeSubset = function(subset) {
        $scope.ds = subset;
    }
    $scope.addLinkset = function(linkset) {
        $scope.ds.linkset.push(linkset);
    }
    $scope.addAgent = function(agent) {
        $scope.ds.agent.push(agent);
    }
   $scope.addApplication = function(agent) {
        $scope.ds.agent.push(agent);
    }
	
    $scope.addExp = function(expe) {
        $scope.ds.expe.push(expe);
    }
	$scope.addExpConf = function(expeconf) {
        $scope.ds.expeconf.push(expeconf);
    }
	
	$scope.addFeatureExp = function(feature) {
        $scope.ds.feature.push(feature);
    }
	
    $scope.addActiveExec = function(active) {
        $scope.ds.active.push(active);
    }
			
    $scope.addParameterAlgo = function(parameter) {
        $scope.ds.parameter.push(parameter);
    }
	
    $scope.addPerfomance = function(perfomance) {
        $scope.ds.perfomance.push(perfomance);
    }

   $scope.addAlgo = function(algo) {
        $scope.ds.algo.push(algo);
    }
	

    $scope.removeLinkset = function(linkset) {
        var index = $scope.ds.linkset.indexOf(linkset);
        $scope.ds.linkset.splice(index, 1);
    }
    $scope.removeAgent = function(agent) {
        var index = $scope.ds.agent.indexOf(agent);
        $scope.ds.agent.splice(index, 1);
    }
	$scope.removeApplication = function(agent) {
        var index = $scope.ds.agent.indexOf(agent);
        $scope.ds.agent.splice(index, 1);
    }
	$scope.removeExp= function(expe) {
        var index = $scope.ds.expe.indexOf(expe);
        $scope.ds.expe.splice(index, 1);
    }
	
	$scope.removeExpConf= function(expeconf) {
        var index = $scope.ds.expeconf.indexOf(expeconf);
        $scope.ds.expeconf.splice(index, 1);
    }
	
    $scope.removeActiveExec= function(active) {
        var index = $scope.ds.active.indexOf(active);
        $scope.ds.active.splice(index, 1);
    }
	
	$scope.removeFeatureExp= function(feature) {
        var index = $scope.ds.feature.indexOf(feature);
        $scope.ds.feature.splice(index, 1);
    }
	
	  $scope.removeParameterAlgo= function(parameter) {
        var index = $scope.ds.parameter.indexOf(parameter);
        $scope.ds.parameter.splice(index, 1);
    }
	
	$scope.removePerfomance= function(perfomance) {
        var index = $scope.ds.perfomance.indexOf(perfomance);
        $scope.ds.perfomance.splice(index, 1);
    }
	
	$scope.removeAlgo= function(algo) {
        var index = $scope.ds.algo.indexOf(algo);
        $scope.ds.algo.splice(index, 1);
    }

    $scope.addDistribution = function(dist) {
        $scope.ds.distribution.push(dist);
    }

    $scope.removeDistribution = function(distribution) {
        var index = $scope.ds.distribution.indexOf(distribution);
        $scope.ds.distribution.splice(index, 1);
    }

    $scope.validateURL = function(textval) {
        var urlregex = new RegExp(
                "^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)*((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(\:[0-9]+)*(/($|[a-zA-Z0-9\.\,\?\'\\\+&amp;%\$#\=~_\-]+))*$");
        return urlregex.test(textval);
    }

    clone = function(obj) {
        var copy;

        // Handle the 3 simple types, and null or undefined
        if (null == obj || "object" != typeof obj)
            return obj;

        // Handle Date
        if (obj instanceof Date) {
            copy = new Date();
            copy.setTime(obj.getTime());
            return copy;
        }

        // Handle Array
        if (obj instanceof Array) {
            copy = [];
            for (var i = 0, len = obj.length; i < len; i++) {
                copy[i] = clone(obj[i]);
            }
            return copy;
        }

        // Handle Object
        if (obj instanceof Object) {
            copy = {};
            for (var attr in obj) {
                if (attr != 'parentDataset')
                    if (obj.hasOwnProperty(attr))
                        copy[attr] = clone(obj[attr]);
            }
            return copy;
        }

        throw new Error("Unable to copy obj! Its type isn't supported.");
    }
    $scope.processForm = function(a) {
        $scope.progressBarWidth = 30;

        console.log($scope.object.dataSets[0]);
        copiedDatasetFinal = clone($scope.object);

        $http({
            method: 'POST',
            url: 'genRdf.php',
            data: $.param(copiedDatasetFinal), // pass in data as strings
            //          data: $.param($scope.object), // pass in data as strings;
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
        })
                .success(function(data) {
                    if (!$scope.showValidation)
                        $scope.progressBarWidth = 0;
                    else
                        $scope.progressBarWidth = 70;
                    $scope.rdf = data;
                    $("#rdfOutput").show();
                    $scope.spanError = "";
                    $("#rdfOutput").dialog({maxHeight: 550, width: 700, zIndex: 500, title: "Mex RDF", position: ['center', 20]});
                    if ($scope.showValidation) {
                        send = new Object();
                        send.s = data;
                        send.t = 'text';
                        send.i = 'turtle';
                        send.csurl = 'http://localhost:8787/validate';

                        $http({
                            method: 'POST',
                            url: 'proxy.php',
                            data: $.param(send),
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
                        })
                                .success(function(data) {
                                    $scope.progressBarWidth = 0;
                                    var bodyhtml = data.split('<body>').pop().split('</body>')[0];
                                    $("#rdfOutputValidatorHTML").html(bodyhtml);
                                    $("#rdfOutputValidator").dialog({maxHeight: 550, width: 900, title: "MEX Validator"});

                                });
                    }


                })
                .error(function(data, status, headers, config) {
                    console.log("Error using validator");
                    $scope.progressBarWidth = 0;
                    $scope.spanError = "Mex file generation was failed.";
                });
        ;

    }

    $scope.editDist = function(dist) {
        console.log(dist);
        var modalInstance = $modal.open({
            templateUrl: 'modalDistContent.html',
            controller: ModalEditDistInstanceCtrl,
            size: 'lg',
            resolve: {
                dist: function() {
                    return {dist: dist, messages: $scope.messages, license: $scope.license};
                }
            }
        });
        modalInstance.result.then(function(dist) {
        }, function() {
            console.log('Modal dismissed at: ' + new Date());
        });

    };
		
	    $scope.editApplication = function(agent) {
        var modalInstance = $modal.open({
            templateUrl: 'modalAgentApplication.html',
            controller: ModalEditAgentInstanceCtrl,
            size: 'md',
            resolve: {
                agent: function() {
                    return {agent: agent, messages: $scope.messages};
                }
            }
        });
    };
	
	   $scope.editExp= function(expe) {
        var modalInstance = $modal.open({
            templateUrl: 'modalAgentExp.html',
            controller: ModalEditExpInstanceCtrl,
            size: 'md',
            resolve: {
                expe: function() {
                    return {expe: expe, messages: $scope.messages};
                }
            }
        });
    };
	
		   $scope.editExpConf= function(expeconf) {
        var modalInstance = $modal.open({
            templateUrl: 'modalAgentExpConf.html',
            controller: ModalEditExpConfInstanceCtrl,
            size: 'md',
            resolve: {
                expeconf: function() {
                    return {expeconf: expeconf, messages: $scope.messages};
                }
            }
        });
    };
	
$scope.editActiveExec = function(active) {
        var modalInstance = $modal.open({
            templateUrl: 'modalActiveExec.html',
            controller: ModalEditActiveInstanceCtrl,
			
            size: 'md',
            resolve: {
                active: function() {
                    return {active: active, messages: $scope.messages};
                }
            }
        });
    };
	
$scope.editFeatureExp = function(feature) {
        var modalInstance = $modal.open({
            templateUrl: 'modalFeatureExp.html',
            controller: ModalEditFeatureInstanceCtrl,
			
            size: 'md',
            resolve: {
                feature: function() {
                    return {feature: feature, messages: $scope.messages};
                }
            }
        });
    };
	
	$scope.editParameterAlgo = function(parameter) {
        var modalInstance = $modal.open({
            templateUrl: 'modalParameterAlgo.html',
            controller: ModalEditParameterInstanceCtrl,
            size: 'md',
            resolve: {
                parameter: function() {
                    return {parameter: parameter, messages: $scope.messages};
                }
            }
        });
    };
	
		$scope.editPerfomance = function(perfomance) {
        var modalInstance = $modal.open({
            templateUrl: 'modalPerfomance.html',
            controller: ModalEditPerfomanceInstanceCtrl,
			
            size: 'md',
            resolve: {
                perfomance: function() {
                    return {perfomance: perfomance, messages: $scope.messages};
                }
            }
        });
    };

	
	$scope.editAlgo = function(algo) {
        var modalInstance = $modal.open({
            templateUrl: 'modalAlgo.html',
            controller: ModalEditAlgoInstanceCtrl,
            size: 'md',
            resolve: {
                algo: function() {
                    return {algo: algo, messages: $scope.messages};
                }
            }
        });
    };
	
	
    $scope.parseExternalDistributions = function(a) {
        $scope.progressBarWidth = 30;


        b = new Object();
        b.address = a.address;
        b.mediaType = a.mediaType;
        b.description = a.description;
        
        console.log(b.description+"!");

        $http({
            method: 'POST',
            url: 'parser/parser.php',
            data: $.param(b), // pass in data as strings
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
        })
                .success(function(data) {
                    $scope.progressBarWidth = 70;
                    for (i in data) {
                        $scope.ds.distribution.push({
                            'accessUrl': b.address + data[i].name,
                            'title': data[i].name,
                            'mediaType': b.mediaType,
                            'description': b.description,
                            'issued': data[i].date,
                            'format': data[i].name.split(".").pop(-1),
//                            'description': data[i].description,
                            'prop': 'dcat:distribution'});

                    }

                    $scope.progressBarWidth = 0;

                }).error(function(data, status, headers, config) {
                    console.log("Error trying to parse webpage");
                    $scope.progressBarWidth = 0;
                    $scope.spanError = "Parsing webpage failed.";
                });;

    }

    $scope.editLinkset = function(linkset) {
        console.log(linkset);
        var modalInstance = $modal.open({
            templateUrl: 'modalLinksetContent.html',
            controller: ModalEditLinksetInstanceCtrl,
            size: 'md',
            resolve: {
                linkset: function() {
                    return {linkset: linkset, messages: $scope.messages};
                }
            }
        });
        modalInstance.result.then(function(linkset) {
        }, function() {
            console.log('Modal dismissed at: ' + new Date());
        });

    };


    $scope.open = function(size) {
        var modalInstance = $modal.open({
            templateUrl: 'modalContent.html',
            controller: ModalInstanceCtrl,
            size: size,
            resolve: {
                subset: function() {
                    return {subset: {issued: dt, modified: dt}, messages: $scope.messages};
                }
            }
        });
        modalInstance.result.then(function(subset) {
            $scope.ds.subset.push(subset);
        }, function() {
            console.log('Modal dismissed at: ' + new Date());
        });

    };
    $scope.openUploadDataHub = function(size) {
        var modalInstance = $modal.open({
            templateUrl: 'modalUploadDataHub.html',
            controller: ModalUploadDataHubCtrl,
            size: size,
            resolve: {
                dataHubObj: function() {
                    return {};
                }
            }
        });
        modalInstance.result.then(function(dataHubObj) {
            console.log("DataHub Post Here");
        }, function() {
            console.log('Modal dismissed at: ' + new Date());
        });

    };

    $scope.openDist = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalDistContent.html',
            controller: ModalDistInstanceCtrl,
            size: size,
            resolve: {
                dist: function() {
                    return {dist: {prop: 'void:sparqlEndpoint', title: ''}, messages: $scope.messages, license: $scope.license};
                }
            }
        });
        modalDistInstance.result.then(function(dist) {
            if (dist.title != '')
                $scope.ds.distribution.push(dist);
        });

    };
    $scope.openDistWeb = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalDistWebContent.html',
            controller: ModalDistWebInstanceCtrl,
            size: size,
            resolve: {
                parseAddr: function() {
                    return {address: ''};
                }
            }
        });
        modalDistInstance.result.then(function(a) {
            if (a.address != '')
                $scope.parseExternalDistributions(a);
        });

    };

	   $scope.openApplication= function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalAgentApplication.html',
            controller: ModalAgentInstanceCtrl,
            size: size,
            resolve: {
                agent: function() {
                    return {agent: {prop: 'prov:actedOnBehalfOf ', resource: '', name: '', label: ''}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(agent) {
            if (agent.resource != '' || agent.name != '' ||  agent.label != '')
                $scope.ds.agent.push(agent);

        });
    };
	
	 $scope.openExp = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalAgentExp.html',
            controller: ModalExpInstanceCtrl,
            size: size,
            resolve: {
                expe: function() {
                    return {expe: {samp: 'mexcore:hasSamplingMethod', resource: '', trainsize: '', testsize: '', folds: '',sequential:''}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(expe) {
            if (expe.resource != '' || expe.samp !=  '' || expe.train != '' || expe.test != '' || expe.folds != '' || expe.sequential != '')
                $scope.ds.expe.push(expe);

        });
    };
	var config = 1;
	 $scope.openExpConf = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalAgentExpConf.html',
            controller: ModalExpConfInstanceCtrl,
            size: size,
            resolve: {
                expeconf: function() {
                    return {expeconf: {iden: 'configuration'+config, resource: '', desc:'', os:'', cpu:'', memory:'', hdType:'', videoGraphs:'', cache:'', titleDset:'', descriDset:'', landingPage:'', trainsize:'', trainsize: '', testsize: '', folds: '',sequential:'', softwareName:'', softwareVersion:''}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(expeconf) {
            if (expeconf.resource != '' || expeconf.iden !=  '' || expeconf.desc != '' || expeconf.os != '' || expeconf.cpu != '' || expeconf.memory != '' || expeconf.hdType != ''|| expeconf.videoGraphs != '' || expeconf.cache != '' || expeconf.titleDset != '' || expeconf.descriDset != ''|| expeconf.landingPage != ''|| expeconf.train != '' || expeconf.test != '' || expeconf.folds != '' || expeconf.sequential != ''|| expeconf.softwareName != '' || expeconf.softwareVersion != '')
                $scope.ds.expeconf.push(expeconf);

        });
		config++;
    };
	
	var number = 1;
$scope.openActiveExec = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalActiveExec.html',
            controller: ModalActiveInstanceCtrl,
            size: size,
            resolve: {
                active: function() {
                    return {active: {prop: 'exec'+number, resource: '', desc: '', expe: '', algo:'', startedattime: '', endattime:'', phase:'', modeliden:'model'+number, modeldesc:'', modeldate:'', expeconf: $scope.ds.expeconf, algos: $scope.ds.algo}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(active) {
            if (active.resource != ''|| active.prop!= ''|| active.desc != ''|| active.expe !=  '' || active.algo !=  '' || active.start != '' || active.end != '' || active.phase != '' || active.modeliden != '' || active.modeldesc != '' || active.modeldate != '')
                $scope.ds.active.push(active);
        });
		number++;
    };
	
	var fea = 1;
$scope.openFeatureExp = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalFeatureExp.html',
            controller: ModalFeatureInstanceCtrl,
            size: size,
            resolve: {
                feature: function() {
                    return {feature: {iden: 'feature'+fea, resource: '', value:'', expe:'', collection:$scope.ds.expeconf}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(feature) {
            if (feature.resource != ''|| feature.iden !=  '' || feature.value != '' || feature.expe != '')
                $scope.ds.feature.push(feature);
        });
		fea++;
    };
	
	var param = 1;
$scope.openParameterAlgo = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalParameterAlgo.html',
            controller: ModalParameterInstanceCtrl,
            size: size,
            resolve: {
                parameter: function() {
                    return {parameter: {prop: 'parameter'+param,resource: '', value:'' , algo: '',collection:$scope.ds.algo}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(parameter) {
            if (parameter.resource != ''|| parameter.prop!= ''|| parameter.value != '' || parameter.algo != '')
                $scope.ds.parameter.push(parameter);      		
        });
		param++;
    };
	
		var perf = 1;
$scope.openPerfomance = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalPerfomance.html',
            controller: ModalPerfomanceInstanceCtrl,
            size: size,
            resolve: {
                perfomance: function() {
                    return {perfomance: {iden: 'perfomance'+perf, resource: '',type:'', desc: '', exec:'', collectionexec:$scope.ds.active}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(perfomance) {
            if (perfomance.resource != ''|| perfomance.iden!=  ''|| perfomance.type != ''|| perfomance.desc !=  '' || perfomance.exec !=  '')
                $scope.ds.perfomance.push(perfomance);
        });
		perf++;
    };
	
var algor = 1;
$scope.openAlgo = function(size) {
        var modalDistInstance = $modal.open({
            templateUrl: 'modalAlgo.html',
            controller: ModalAlgoInstanceCtrl,
            size: size,
            resolve: {
                algo: function() {
                    return {algo: {prop: 'algo'+algor, resource: '', exec: '', identifier: '', description: '', acronym: ''}, messages: $scope.messages};
                }
            }
        });
        modalDistInstance.result.then(function(algo) {
            if (algo.resource != ''|| algo.prop!=  ''|| algo.iden != ''|| algo.desc != '' || algo.acro !=    '' )
                $scope.ds.algo.push(algo);
        });
		algor++;
    };
	

    $scope.openLinkset = function(size) {
        var modalInstance = $modal.open({
            templateUrl: 'modalLinksetContent.html',
            controller: ModalLinksetInstanceCtrl,
            size: size,
            resolve: {
                linkset: function() {
                    return {linkset: {issued: dt, modified: dt, prop: 'void:subset'}, messages: $scope.messages};
                }
            }
        });
        modalInstance.result.then(function(linkset) {
            $scope.ds.linkset.push(linkset);
        }, function() {
            console.log('Modal dismissed at: ' + new Date());
        });

    };

    $scope.downloadInnerHtml = function() {
        fileName = 'mex.ttl'; // You can use the .txt extension if you want
        var elHtml = document.getElementById('rdf').textContent;
        var link = document.createElement('a');
        mimeType = 'text/plain';

        link.setAttribute('download', fileName);
        link.setAttribute('href', 'data:' + mimeType + ';charset=utf-8,' + encodeURIComponent(elHtml));
        link.click();
    }


}


var ModalInstanceCtrl = function($scope, $modalInstance, subset) {
    $scope.subset = subset.subset;
    $scope.messages = subset.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.subset);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};
var ModalUploadDataHubCtrl = function($scope, $modalInstance, dataHubObj) {
    $scope.dataHubObj = dataHubObj;
    $scope.ok = function() {
        $modalInstance.close($scope.dataHubObj);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditInstanceCtrl = function($scope, $modalInstance, subset) {
    $scope.subset = subset.subset;
    $scope.messages = subset.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.subset);
    };
    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditDistInstanceCtrl = function($scope, $modalInstance, dist) {
    $scope.distribution = dist.dist;
    $scope.messages = dist.messages;
    $scope.license = dist.license;
    $scope.ok = function() {
        $modalInstance.close($scope.distribution);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};
var ModalEditAgentInstanceCtrl = function($scope, $modalInstance, agent) {
    $scope.agent = agent.agent;
    $scope.messages = agent.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.agent);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditActiveInstanceCtrl = function($scope, $modalInstance, active) {
    $scope.active = active.active;
    $scope.messages = active.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.active);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditFeatureInstanceCtrl = function($scope, $modalInstance, feature) {
    $scope.feature = feature.feature;
    $scope.messages = feature.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.feature);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditExpInstanceCtrl = function($scope, $modalInstance, expe) {
    $scope.expe = expe.expe;
    $scope.messages = expe.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.expe);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditExpConfInstanceCtrl = function($scope, $modalInstance, expeconf) {
    $scope.expeconf = expeconf.expeconf;
    $scope.messages = expeconf.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.expeconf);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditParameterInstanceCtrl = function($scope, $modalInstance, parameter) {
    $scope.parameter = parameter.parameter;
    $scope.messages = parameter.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.parameter);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditPerfomanceInstanceCtrl = function($scope, $modalInstance, perfomance) {
    $scope.perfomance = perfomance.perfomance;
    $scope.messages = perfomance.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.perfomance);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditPhaseInstanceCtrl = function($scope, $modalInstance, phase) {
    $scope.phase = phase.phase;
    $scope.messages = phase.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.phase);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditAlgoInstanceCtrl = function($scope, $modalInstance, algo) {
    $scope.algo = algo.algo;
    $scope.messages = algo.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.algo);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalDistInstanceCtrl = function($scope, $modalInstance, dist) {
    $scope.distribution = dist.dist;
    $scope.messages = dist.messages;
    $scope.license = dist.license;
    $scope.ok = function() {
        $modalInstance.close($scope.distribution);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};
var ModalDistWebInstanceCtrl = function($scope, $modalInstance, parseAddr) {

    $scope.address2 = parseAddr.address;
    $scope.ok = function(addr, format, description) {
        console.log(addr + format);
        a = new Object();
        a.address = addr;
        a.mediaType = format;
        a.description = description;
        $modalInstance.close(a);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};
var ModalAgentInstanceCtrl = function($scope, $modalInstance, agent) {
    $scope.agent = agent.agent;
    $scope.messages = agent.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.agent);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalExpInstanceCtrl = function($scope, $modalInstance, expe) {
    $scope.expe = expe.expe;
    $scope.messages = expe.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.expe);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalExpConfInstanceCtrl = function($scope, $modalInstance, expeconf) {
    $scope.expeconf = expeconf.expeconf;
    $scope.messages = expeconf.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.expeconf);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalActiveInstanceCtrl = function($scope, $modalInstance, active) {
    $scope.active = active.active;
    $scope.messages = active.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.active);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalFeatureInstanceCtrl = function($scope, $modalInstance, feature) {
    $scope.feature = feature.feature;
    $scope.messages = feature.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.feature);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalParameterInstanceCtrl = function($scope, $modalInstance, parameter) {
    $scope.parameter = parameter.parameter;
    $scope.messages = parameter.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.parameter);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalPerfomanceInstanceCtrl = function($scope, $modalInstance, perfomance) {
    $scope.perfomance = perfomance.perfomance;
    $scope.messages = performance.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.perfomance);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalPhaseInstanceCtrl = function($scope, $modalInstance, phase) {
    $scope.phase = phase.phase;
    $scope.messages = phase.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.phase);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalAlgoInstanceCtrl = function($scope, $modalInstance, algo) {
    $scope.algo = algo.algo;
    $scope.messages = algo.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.algo);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var ModalEditLinksetInstanceCtrl = function($scope, $modalInstance, linkset) {
    $scope.linkset = linkset.linkset;
    $scope.messages = linkset.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.linkset);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};
var ModalLinksetInstanceCtrl = function($scope, $modalInstance, linkset) {
    $scope.linkset = linkset.linkset;
    $scope.messages = linkset.messages;
    $scope.ok = function() {
        $modalInstance.close($scope.linkset);
    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};

var INTEGER_REGEXP = /^\-?\d+$/;
var URL_REGEXP = new RegExp(
        "^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)*((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(\:[0-9]+)*(/($|[a-zA-Z0-9\.\,\?\'\\\+&amp;%\$#\=~_\-]+))*$");

mexGen.directive('url', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {
                if (URL_REGEXP.test(viewValue)) {
                    // it is valid
                    ctrl.$setValidity('url', true);
                    console.log('valid url');
                    return viewValue;
                } else {
                    // it is invalid, return undefined (no model update)
                    ctrl.$setValidity('url', false);
                    console.log('!valid url');
                    return undefined;
                }
            });
        }
    };
});



