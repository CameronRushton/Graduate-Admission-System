//copyFileSync("./aurelia_project/environments/prod.js", "./src")
console.log("Compiling javascript...")
const execSync = require('child_process').execSync;
// import { execSync } from 'child_process';  // replace ^ if using ES modules
const output = execSync('au build --env prod', { encoding: 'utf-8' });  // the default is 'buffer'
console.log('\n', output);

var fs = require('fs');
var path = require('path');

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
    var targetFolder = path.join( target, path.basename( source ) ).replace('\\dist', '');
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

//var directory = "../resources/static"
//fs.readdir(directory, (err, files) => {
//  if (err) throw err;
//
//  for (const file of files) {
//    fs.unlink(path.join(directory, file), err => {
//      if (err) throw err;
//    });
//  }
//});

copyFolderRecursiveSync("./dist", "../resources/static")

