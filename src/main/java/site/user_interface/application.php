<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">ApplicationContext<br /></div>
    </div>
    <div class="panel-body"> 
        <div>
            <br/> 
            <label>Author<span style="color: red">*</span></label> 
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_givenName'> foaf:givenName </a>    
            <br>                
          <input required placeholder="" type="text" class="form-control" ng-model="ds.givenName">
            <br/>
            <label>Creator Email <span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_mbox'> foaf:mbox </a>   
            <br>                
          <input required placeholder="" class="form-control" type="text" ng-model="ds.mbox">
            <br/>
            <label>Project Homepage <span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#homepage'> doap:homepage </a>   
            <br>                
          <input required placeholder="" class="form-control" name="homePage" url size="51" type="text" ng-model="ds.homePage">
            <br/>
             <label>Url<span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://www.w3.org/2000/01/rdf-schema#'> rdfs:seeAlso</a>   
            <br>                
          <input required placeholder="" class="form-control" name="seeAlso" url size="51" type="text" ng-model="ds.seeAlso">
            <br/>
            <label>Copyright Date<span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://purl.org/dc/terms/'> dct:dateCopyrighted</a>   
            <br>                
          <input required placeholder="" class="form-control" name="copyDate"  type="text" ng-model="ds.copyDate">
            <br/>
           	<label>Context<span style="color: red">*</span></label>
                <select class="form-control" ng-model="ds.context">
                    <option>AdaptativeWebSites</option>
                     <option>AffectiveComputing</option>
                      <option>LinkDiscovery</option>
                      <option>Bioinformatics</option>
                      <option>BrainMachineInterfaces</option>
                      <option>Cheminformatics</option>
                      <option>ClassifyingDNAsequences</option>
                      <option>ComputationalAdversiting</option>
                      <option>ComputationalFinance</option>
                      <option>ComputerVision</option>
                     <option>DetectingCreditCardFrauds</option>
                      <option>FactPrediction</option>
                      <option>GamePlaying</option>
                      <option>InformationRetrieval</option>
                      <option>InternetFraudDetection</option>
                      <option>MachinePerception</option>
                      <option>MedicalDiagnosis</option>
                      <option>Metaheuristics</option>
                      <option>NaturalLanguageProcessing</option>
                     <option>ObjectRecognition</option>
                      <option>Optimization</option>
                      <option>RecomenderSystems</option>
                      <option>Robotics</option>
                      <option>SearchEngines</option>
                      <option>SentimentAnalysis</option>
                      <option>SequenceMining</option>
                      <option>SoftwareEngineering</option>
                      <option>SpeechAndHandwritingRecognition</option>
                     <option>StockMarketAnalysis</option>
                      <option>StructuralHealthMonitoring</option>
                      <option>SyntacticLanguageProcessing</option>
                </select>
        </div>  
        <br />
        <br />
                <input type="reset" class="btn btn-default" value="Reset">
    </div>
</div>