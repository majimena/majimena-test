// Karma configuration
// http://karma-runner.github.io/0.10/config/configuration-file.html

module.exports = function (config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: '../../',

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [
            // bower:js
            'main/webapp/bower_components/modernizr/modernizr.js',
            'main/webapp/bower_components/jquery/dist/jquery.js',
            'main/webapp/bower_components/bootstrap/dist/js/bootstrap.js',
            'main/webapp/bower_components/json3/lib/json3.js',
            'main/webapp/bower_components/angular/angular.js',
            'main/webapp/bower_components/angular-ui-router/release/angular-ui-router.js',
            'main/webapp/bower_components/angular-resource/angular-resource.js',
            'main/webapp/bower_components/angular-cookies/angular-cookies.js',
            'main/webapp/bower_components/angular-sanitize/angular-sanitize.js',
            'main/webapp/bower_components/angular-translate/angular-translate.js',
            'main/webapp/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
            'main/webapp/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js',
            'main/webapp/bower_components/angular-dynamic-locale/src/tmhDynamicLocale.js',
            'main/webapp/bower_components/angular-local-storage/dist/angular-local-storage.js',
            'main/webapp/bower_components/angular-cache-buster/angular-cache-buster.js',
            'main/webapp/bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js',
            'main/webapp/bower_components/datatables/media/js/jquery.dataTables.js',
            'main/webapp/bower_components/flot/jquery.flot.js',
            'main/webapp/bower_components/holderjs/holder.js',
            'main/webapp/bower_components/metisMenu/dist/metisMenu.js',
            'main/webapp/bower_components/raphael/raphael.js',
            'main/webapp/bower_components/mocha/mocha.js',
            'main/webapp/bower_components/morrisjs/morris.js',
            'main/webapp/bower_components/datatables-responsive/js/dataTables.responsive.js',
            'main/webapp/bower_components/flot.tooltip/js/jquery.flot.tooltip.js',
            'main/webapp/bower_components/startbootstrap-sb-admin-2/dist/js/sb-admin-2.js',
            'main/webapp/bower_components/angular-mocks/angular-mocks.js',
            // endbower
            'main/webapp/scripts/app/app.js',
            'main/webapp/scripts/app/**/*.js',
            'main/webapp/scripts/components/**/*.{js,html}',
            'test/javascript/**/!(karma.conf).js'
        ],


        // list of files / patterns to exclude
        exclude: [],

        // web server port
        port: 9876,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false
    });
};
