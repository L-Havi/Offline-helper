package Utilities.Lists;

import java.util.ArrayList;
import java.util.List;

public class ExtensionList {
	
	public List<String> getAllExtensions(){
		
		String[] extensions = {"3ds","3dsx","3GP","3G2","3MF","7z","4TH","a","aac","accdb","accft","adame","adicht","adt","adx","adz","agda","agr","ahk","ai","aiff","aifc",
				"aio","air","akm","akp","ale","all","amf","amg","aml","amlx","ampl","amr","amv","ani","ann","ape","apk","ar","arc","art","asax","ascx","asf","at3","aty","avi",
				"avs","awk","axf","aep","b","bak","bar","bas","bat","bdt","beam","bin","bm3","bmp","bps","bz2","blend","c--","c","c++","cpp","c32","cab","cbt","cbz","cc","cd",
				"cdf","cdr","cer","cgm","chk","chm","cho","cia","cif","class","clip","cmd","cmod","cn1","coe","com","compile","copac","cpc","cpc","cpl","cr2","crai","crdownload",
				"crt","cs","csproj","css","csv","cur","d","d3v","d4d","d4p","d64","d88","daa","dae","daf","dal","dart","dat","dats","dav","db$","db","db2","db3","dba","dbc","dbd",
				"dbf","dbfx","dbg","dbk","dbl","dbm","dbo","dbpro","dbs","dbt","dbw","dbx","dc","dc6","dca","dcc","dcd","dch","dci","dcl","dcm","dcp","dcs","dct","dcu","dcw",
				"dcx","dd","dda","ddb","ddd","ddi","ddp","dds","deb","def","dem","der","des","desklink","desktop","dev","dex","dfd","dff","dfi","dfl","dfm","dfpwm","dfs","dfti",
				"dfv","dfx","dgn","dgs","dh","dhp","dht","di","dia","diagcab","dib","dicom","dif","dig","dip","dir","dired","dis","divx","diz","djvu","dkb","dl","dlc","dld","dlf",
				"dlg","dll","dlm","dlp","dls","dme","dmf","dmg","dmmdl","dmo","dmp","dmv","dmw","dn","dng","doc","docm","docx","dochtml","docmhtml","dot","dotm","dotx","dothtml",
				"dof","dog","doh","dol","dor","dot","dotx","download","dpf","dpx","drc","dru","ds","dsc","dtd","dvc","dw2","dwf","dwg","dxf","dxp","dylib","e","e##","e00","e2d","e57",
				"ebd","ec","ecc","ede","edf","efi","eis","el","elc","elf","email","emaker","emf","eml","emz","eot","ep","epa","epc","eps","epub","erl","es6","escpcb","escsch","esd",
				"etl","evt","evtx","ex","exe","exp","exs","ezw","f","f01","f03","f08","f18","f4","f4v","f77","f90","f95","fa","faa","factor","fasta","fastq","fb","fb2","fb2k-component",
				"fbx","fen","ff","ffn","fits","flac","flame","flp","flv","fna","fni","fnx","fodg","fodp","fods","fodt","for","fql","frag","frm","fs","fth","gbr","gdf","gdt","gdx",
				"ged","ggb","gif","gmi","gmk","gml","go","gpx","grp","gtf","gv","gz","h!","h--","h","h++","hc","ha","hack","har","hdi","hdmp","hh","hof","hpp","hta","htm","html",
				"hum","hxx","icc","ice","icl","ico","ifc","igc","iges","img","ini","io","ipt","irx","iso","it","jl","j2c","jar","jav","java","jbig","jnlp","jp2","jpeg","jpg","js","json",
				"jsp","key","kml","kmz","ko","kra","kt","kve","kv","label","lbr","ldb","ldt","lha","lisp","ll","lnk","lrc","lua","lxf","ly","lz","m","m2ts","m3u","m3u8","m4a",
				"m4p","m4r","m4v","m64","mcf","md","mdf","mdi","mdg","mdl","mds","mex","mgf","mid","mk3d","mka","mks","mkv","ml","mm","mmdc","mmip","mmpz","mnt","mobi","mod","modules",
				"mol","mol2","mop","mov","mp2","mp3","mp4","mpa","mpc","mpd","mpeg","mpg","msc","mscx","mscz","msdl","msf","msi","mso","msstyles","msu","mts","mxf","myd","myi","nb",
				"nc","ncd","nef","neu","nfo","nim","nmf","npr","nrw","ns1","nsa","nsf","nsv","numbers","nwd","nwf","o","obj","obs","ocx","odb","odf","odg","odp","ods","odt","oga",
				"ogg","ogv","ogx","opus","org","osc","osk","osm","ost","osz","otb","otf","otl","otg","otp","ots","ott","ov2","owl","oxt","p","p10","p12","pack","pages","pak","pal",
				"pam","par","par2","pas","pblib","pbo","pcl","pcs","pdb","pde","pdf","pdi","pdm","pdn","pds","pem","pet","pfa","pfam","pfb","pfc","pfm","pgn","pfx","php","php3",
				"php4","phy","phf","phr","pi2","pie","pir","pit","pkl","pka","pl","pli","plr","pm","pma","png","pom","pptx","ppsx","prj","proto","prp","ps","psd","psdc","psm1",
				"psppalette","pst","ps1","ptf","pts","ptx","pub","pup","py","qfx","qif","qlc","qt","qtvr","r","r00","r01","r2d","r3d","r8p","rad","ral","ram","rap","rar","ras",
				"rb","rbxm","rbxl","rblx","rc","rdp","rds","res","rkt","rm","rmd","rmvb","rol","rpm","rs","rsl","rsls","rslf","rst","rtf","run","s","s3m","s7i","saif","sass",
				"sat","sav","savegame","sb","sb2","sb3","sbh","sbv","sbx","scala","scm","scr","scss","sdf","sd7","sds","sdts","search-ms","sec","sed","seq","sf","sfb","sfc",
				"sfx","sh","shar","shtm","shtml","shx","sig","sl","sm","smclvl","smk","sno","so","spf","spiff","spin","sps","spt","spx","spz","sql","ssc","st","stc","std","sti",
				"stk","stl","stm","sto","stp","sts","stw","sur","svc","svg","svp","swatches","swf","swg","swift","swm","sxc","sxd","sxg","sxi","sxm","sxp","sxw","sylk","symboliclink",
				"tak","tar","taz","tbg","tbl","tc","tga","thm","tif","tiff","tlb","tmp","tns","torrent","ts","tsv","tt","ttc","ttf","txt","tga","ui","ump","unv","uos","uot","ups",
				"url","usdz","ust","ut!","v","v4p","v64","vb","vbox","vbox-extpack","vbproj","vbr","vbs","vbx","vc6","vcls","vda","vdi","vdw","vdx","vfd","vi","vmcz","vmdk",
				"vmg","vob","vpk","vpm","vpp","vpr","vqm","vrb","vs","vsd","vsdx","vsm","vsq","vsqx","vst","vsto","vsvnbak","vtf","vue","vvvvvv","wav","war","wbfs","webm","webp",
				"wick","witness_campaign","wk1","wk3","wks","wld","wlmp","wma","wmdb","wmf","wmv","wos","x","x3d","xar","xbrl","xcf","xdm","xe","xex","xls","xlsb","xlsm","xlsx",
				"xm","xml","xmf","xp","xpl","xps","xsd","xsf","xsl","xslt","xsn","xspf","xx","xxe","xxx","xyz","xz","y","yml","yaml","z","z64","zip","zrx","zs"}; 
		
		List<String> extensionList = new ArrayList<String>(); 
		for(String extension:extensions){  
			extensionList.add(extension);  
		}  
		
		return extensionList;
		
	}
}
