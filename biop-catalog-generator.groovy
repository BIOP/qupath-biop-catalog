import qupath.ext.extensionmanager.core.catalog.*
import com.google.gson.GsonBuilder
import com.google.gson.FieldNamingPolicy
import java.nio.file.Paths

def extensionList = []
def savingFolder = "D:/Remy/Github-projects/qupath-biop-catalog/catalog.json"
def qupathMinVersionRange = new VersionRange("v0.6.0", null, null)

// cellpose extension
def cellposeTagList = ["v0.11.0"] 
def cellposeVersionList = []
cellposeTagList.each{tag->
    var cellposeRelease = new Release(
       tag,
       new URI("https://github.com/BIOP/qupath-extension-cellpose/releases/download/"+tag+"/qupath-extension-cellpose-"+tag[1..-1]+".zip"),
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
   new URI("https://github.com/BIOP/qupath-extension-cellpose"),
   false,
   cellposeVersionList
)
extensionList.add(cellposeExtension)

/*
// abba extension
def abbaTagList = ["0.3.3"] 
def abbaVersionList = []
abbaTagList.each{tag->
    var abbaRelease = new Release(
       "v"+tag,
       new URI("https://github.com/BIOP/qupath-extension-abba/releases/download/"+tag+"/qupath-extension-abba-"+tag+".zip"),
       null,
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
   new URI("https://github.com/BIOP/qupath-extension-abba"),
   false,
   abbaVersionList
)
extensionList.add(abbaExtension)


// warpy extension
def warpyTagList = ["0.3.1"] 
def warpyVersionList = []
warpyTagList.each{tag->
    var warpyRelease = new Release(
       "v"+tag,
       new URI("https://github.com/BIOP/qupath-extension-warpy/releases/download/"+tag+"/qupath-extension-warpy-"+tag+".zip"),
       null,
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
   new URI("https://github.com/BIOP/qupath-extension-warpy"),
   false,
   warpyVersionList
)
extensionList.add(warpyExtension)*/


// spotiflow extension
/*def spotiflowTagList = ["v0.2.0"] 
def spotiflowVersionList = []
spotiflowTagList.each{tag->
    var spotiflowRelease = new Release(
       tag,
       new URI("https://github.com/BIOP/qupath-extension-spotiflow/releases/download/"+tag+"/qupath-extension-spotiflow-"+tag[1..-1]+".jar"),
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
   new URI("https://github.com/BIOP/qupath-extension-spotiflow"),
   false,
   spotiflowVersionList
)
extensionList.add(spotiflowExtension)*/

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


// biop extension
def biopTagList = ["v3.3.0"] 
def biopVersionList = []
biopTagList.each{tag->
    var biopRelease = new Release(
       tag,
       new URI("https://github.com/BIOP/qupath-extension-biop/releases/download/"+tag+"/qupath-extension-biop-"+tag[1..-1]+".jar"),
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
   new URI("https://github.com/BIOP/qupath-extension-biop"),
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