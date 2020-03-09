const execSync = require('child_process').execSync;
// import { execSync } from 'child_process';  // replace ^ if using ES modules

var environment = "dev"; // 'stage', 'prod'
if (process.argv[2] && process.argv[2] === '-env') {
  if (process.argv[3]) {
  	environment = process.argv[3];
  	console.log("Deploying in " + environment + " environment.")
  } else {
    	console.log("WARN: No value for -env specified, assuming dev.")
    }
} else {
	console.log("WARN: No -env specified, assuming dev.")
}

console.log("Compiling javascript...")

const output = execSync('au build --env ' + environment, { encoding: 'utf-8' });  // the default is 'buffer'
console.log('\n', output);

var fs = require('fs');
var path = require('path');
var directory = "../resources/static";
var rimraf = require("rimraf");
rimraf.sync(directory);

copyFolderRecursiveSync("./dist", "../resources/static")

function copyFileSync( source, target ) {

    var targetFile = target;

    //if target is a directory a new file with the same name will be created
    if ( fs.existsSync( target ) ) {
        if ( fs.lstatSync( target ).isDirectory() ) {
            targetFile = path.join( target, path.basename( source ) );
        }
    }
	console.log("writing file " + targetFile)
    fs.writeFileSync(targetFile, fs.readFileSync(source));
}

function copyFolderRecursiveSync( source, target ) {
    var files = [];

    //check if folder needs to be created or integrated
    var targetFolder = path.join( target, path.basename( source ) ).replace('\\dist', '').replace('dist', '');
	if ( !fs.existsSync( targetFolder ) ) {
		fs.mkdirSync( targetFolder );
	}

    //copy
    if ( fs.lstatSync( source ).isDirectory() ) {
        files = fs.readdirSync( source );
        files.forEach( function ( file ) {
            var curSource = path.join( source, file );
            if ( fs.lstatSync( curSource ).isDirectory() ) {
                copyFolderRecursiveSync( curSource, targetFolder );
            } else {
                copyFileSync( curSource, targetFolder );
            }
        } );
    }
}

