/**
 * QuPath BIOP Extension Catalog Generator
 * 
 * Generates a JSON catalog file for QuPath extensions maintained by the BIOP team.
 * The catalog is used by QuPath's extension manager for easy installation and management.
 * 
 * Included extensions:
 * - Cellpose extension - Cell segmentation
 * - ABBA extension - Atlas alignment  
 * - Warpy extension - Image transformations
 * - BIOP extension - Utility functions
 * - Spotiflow extension - Point detection
 * 
 * Usage: Run in QuPath script editor. Update 'savingFolder' path as needed.
 * Output: catalog.json file for use with QuPath extension manager
 * 
 * @author BIOP
 * @version 1.0
 * @requires QuPath v0.6.0+
 */

// --------------- YOU NEED TO MODIFY THIS VARIABLE BEFORE RUNNING IT
def savingFolder = "F:/IAGitLab/qupath-biop-catalog/catalog.json" // Nico
//def savingFolder = "D:/Remy/Github-projects/qupath-biop-catalog/catalog.json" // Rémy


// Versions for packages which are common between different extensions

def imglib2_realtransform_version = "3.1.2"
def imglib2_version = "5.12.0"
def jitk_tps_version = "3.0.2"
def ejml_version = "0.24"
def jama_version = "1.0.2"

def mvn_sj_url = "https://maven.scijava.org/repository/releases/"
def mvn_central_url = "https://repo1.maven.org/maven2/"

def gh_biop_url = "https://github.com/BIOP/"

def qupathMinVersionRange = new VersionRange("v0.6.0", null, null)

def extensionList = []

// ------------------------ CELLPOSE EXTENSION

def cellposeTagList = ["v0.11.0"] 
def cellposeVersionList = []
cellposeTagList.each{tag->
    var cellposeRelease = new Release(
       tag,
       new URI(gh_biop_url+"qupath-extension-cellpose/releases/download/"+tag+"/qupath-extension-cellpose-"+tag[1..-1]+".zip"),
       null,
       null,
       null,
       qupathMinVersionRange
    )
    
    cellposeVersionList.add(cellposeRelease)
}
var cellposeExtension = new Extension(
   "QuPath Cellpose extension",
   "QuPath extension to use Cellpose",
   "BIOP",
   new URI(gh_biop_url + "qupath-extension-cellpose"),
   false,
   cellposeVersionList
)
extensionList.add(cellposeExtension)


// ------------------------ WARPY EXTENSION
def warpyTagList = ["0.4.2"] 
def warpyVersionList = []
warpyTagList.each{tag->
    var warpyRelease = new Release(
       "v"+tag,
                   new URI(gh_biop_url    +"qupath-extension-warpy/releases/download/"         +tag+                          "/qupath-extension-warpy-"+tag+                          ".jar"),
           List.of(new URI(mvn_sj_url     +"net/imglib2/imglib2-realtransform/"                +imglib2_realtransform_version+"/imglib2-realtransform-" +imglib2_realtransform_version+".jar"), // real-transform
                   new URI(mvn_central_url+"net/imglib2/imglib2/"                              +imglib2_version+              "/imglib2-"               +imglib2_version+              ".jar"), // imglib2
                   new URI(mvn_sj_url     +"jitk/jitk-tps/"                                    +jitk_tps_version+             "/jitk-tps-"              +jitk_tps_version+             ".jar"), // jitk-tps
                   new URI(mvn_central_url+"com/googlecode/efficient-java-matrix-library/ejml/"+ejml_version+                 "/ejml-"                  +ejml_version+                 ".jar"), // ejml
                   new URI(mvn_central_url+"gov/nist/math/jama/"                               +jama_version+                 "/jama-"                  +jama_version+                 ".jar")  // jama
           ),
       null,
       null,
       qupathMinVersionRange
    )
    
    warpyVersionList.add(warpyRelease)
}
var warpyExtension = new Extension(
   "QuPath Warpy extension",
   "Warpy - QuPath extension that supports transformations (affine, spline) of images.",
   "BIOP",
   new URI(gh_biop_url + "qupath-extension-warpy"),
   false,
   warpyVersionList
)
extensionList.add(warpyExtension)


// ------------------------ ABBA EXTENSION
def abbaTagList = ["0.4.0"] 
def abbaVersionList = []
abbaTagList.each{tag->
    var abbaRelease = new Release(
       "v"+tag,
                   new URI(gh_biop_url    +"qupath-extension-abba/releases/download/"          +tag+                          "/qupath-extension-abba-" +tag+                          ".jar"),
           List.of(new URI(mvn_sj_url     +"net/imglib2/imglib2-realtransform/"                +imglib2_realtransform_version+"/imglib2-realtransform-" +imglib2_realtransform_version+".jar"), // real-transform
                   new URI(mvn_central_url+"net/imglib2/imglib2/"                              +imglib2_version+              "/imglib2-"               +imglib2_version+              ".jar"), // imglib2
                   new URI(mvn_sj_url     +"jitk/jitk-tps/"                                    +jitk_tps_version+             "/jitk-tps-"              +jitk_tps_version+             ".jar"), // jitk-tps
                   new URI(mvn_central_url+"com/googlecode/efficient-java-matrix-library/ejml/"+ejml_version+                 "/ejml-"                  +ejml_version+                 ".jar"), // ejml
                   new URI(mvn_central_url+"gov/nist/math/jama/"                               +jama_version+                 "/jama-"                  +jama_version+                 ".jar"), // jama
                   new URI(gh_biop_url    +"qupath-extension-warpy/releases/download/"         +warpyTagList[0]+              "/qupath-extension-warpy-"+warpyTagList[0]+              ".jar")  // warpy
      
           ),
       null,
       null,
       qupathMinVersionRange
    )
    
    abbaVersionList.add(abbaRelease)
}
var abbaExtension = new Extension(
   "QuPath ABBA extension",
   "QuPath extension to use Aligning Big Brain and Atlases",
   "BIOP",
   new URI(gh_biop_url + "qupath-extension-abba"),
   false,
   abbaVersionList
)
extensionList.add(abbaExtension)

// spotiflow extension
def spotiflowTagList = ["v0.2.0"] 
def spotiflowVersionList = []
spotiflowTagList.each{tag->
    var spotiflowRelease = new Release(
       tag,
       new URI(gh_biop_url + "qupath-extension-spotiflow/releases/download/"+tag+"/qupath-extension-spotiflow-"+tag[1..-1]+".jar"),
       null,
       null,
       null,
       qupathMinVersionRange
    )
    
    spotiflowVersionList.add(spotiflowRelease)
}
var spotiflowExtension = new Extension(
   "QuPath Spotiflow extension",
   "QuPath extension to use Spotiflow",
   "BIOP",
   new URI(gh_biop_url + "qupath-extension-spotiflow"),
   false,
   spotiflowVersionList
)
extensionList.add(spotiflowExtension)

/*
// hrm extension
def hrmTagList = ["v1.1.0"] 
def hrmVersionList = []
def jsoupVersion = "1.15.4"
def socVersion = "5.19.0"
hrmTagList.each{tag->
    var hrmRelease = new Release(
       tag,
       new URI("https://github.com/BIOP/qupath-extension-biop-hrm/releases/download/"+tag+"/qupath-extension-biop-hrm-"+tag[1..-1]+".jar"),
       null,
       List.of(new URI("https://maven.scijava.org/service/local/artifact/maven/redirect?r=central&g=org.jsoup&a=jsoup&v="+jsoupVersion+"&e=jar"), 
               new URI("https://github.com/GReD-Clermont/simple-omero-client/releases/download/"+socVersion+"/simple-omero-client-"+socVersion+".jar")),
       null,
       qupathMinVersionRange
    )
    
    hrmVersionList.add(hrmRelease)
}
var hrmExtension = new Extension(
   "QuPath HRM extension",
   "QuPath extension to send/retrieve data from HRM server.",
   "BIOP",
   new URI("https://github.com/BIOP/qupath-extension-biop-hrm"),
   false,
   hrmVersionList
)
extensionList.add(hrmExtension)*/


// ------------------------ BIOP EXTENSION

def biopTagList = ["v3.4.2", "v3.4.1", "v3.4.0", "v3.3.0"]
def biopVersionList = []
biopTagList.each{tag->
    var biopRelease = new Release(
       tag,
       new URI(gh_biop_url + "qupath-extension-biop/releases/download/"+tag+"/qupath-extension-biop-"+tag[1..-1]+".jar"),
       null,
       null,
       null,
       qupathMinVersionRange
    )
    
    biopVersionList.add(biopRelease)
}
var biopExtension = new Extension(
   "QuPath BIOP extension",
   "QuPath extension containing utilitiy functions by the BIOP",
   "BIOP",
   new URI(gh_biop_url + "qupath-extension-biop"),
   false,
   biopVersionList
)
extensionList.add(biopExtension)


var catalog = new Catalog(
   "QuPath-BIOP catalog",
   "Extensions maintained by the BIOP team",
   extensionList
)


try (FileWriter fileWriter = new FileWriter(savingFolder)) {
   new GsonBuilder()
      .setPrettyPrinting()
      .disableHtmlEscaping()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create()
      .toJson(catalog, fileWriter);
}
println "Catalog saved to " + savingFolder


import qupath.ext.extensionmanager.core.catalog.*
import com.google.gson.GsonBuilder
import com.google.gson.FieldNamingPolicy
import java.nio.file.Paths