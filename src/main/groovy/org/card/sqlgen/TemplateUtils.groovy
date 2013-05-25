package org.card.sqlgen

import groovy.text.GStringTemplateEngine

class TemplateUtils {

    protected static InputStream getResource( String templatePath ) {
        return TemplateUtils.class.classLoader.getResourceAsStream( templatePath )
    }

    public static String render( String templatePath, Map model = [:] ) {
        String template = getResource( templatePath ).text
        return new GStringTemplateEngine().createTemplate( template ).make( model ).toString()
    }

    /**
     * Generates a new file using a provided template and model data.
     *
     * @param filePath
     *      File path in which to create the new file.  This will recursively create
     *      any directory structure that does not already exist.
     * @param templatePath
     *      File path to a <tt>GStringTemplate</tt>
     * @param model
     *      Map of data to pass through to the template
     */
    public static void generateFile( String filePath, String templatePath, Map model = [:] ) {
        int pos = filePath.lastIndexOf( '/' )
        if ( pos > 0 ) {
            String path = filePath.substring( 0,pos )
            new File( path ).mkdirs()
        }
        new File(filePath).text = render(templatePath, model)

        println "Rendered file: ${filePath}"
    }

    /**
     * Append to an existing file.
     *
     * @param filePath
     *      Path of file to append to.
     * @param templatePath
     *      Path to <tt>GStringTemplate</tt> style file that will be used for appending
     * @param model
     *      Map of data to pass through to the template
     */
    public static void appendToFile( String filePath, String templatePath, Map model = [:] ) {
        def f = new File( filePath )
        f.append( render( templatePath, model ) )

        println "Appended to: ${filePath}"
    }
}