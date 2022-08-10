package oap.openapi.maven.plugin.mojo;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

import java.util.List;

/**
 * The <code>openapi</code> Mojo goal.
 * <p/>
 * This Mojo goal generates JSON OpenAPI (swagger) based on its web services.
+ */
@Mojo(
        name = "openapi",
        requiresDependencyResolution = ResolutionScope.COMPILE,
        configurator = "include-project-dependencies"
)
public class OpenApiGeneratorPlugin extends AbstractMojo {

    @Parameter( defaultValue = "${project}", required = true, readonly = true )
    private MavenProject project;
    @Parameter( property = "project.compileClasspathElements", required = true, readonly = true )
    private List<String> classpath;
    @Component
    private PluginDescriptor pluginDescriptor;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info( "OpenAPI generation..." );
        try {
            String projectClass = "some.project.class.ToBeLoaded";
            try {
                ClassRealm realm = pluginDescriptor.getClassRealm();
                Class clazz = realm.loadClass( projectClass );
                processWithClass( clazz );
            } catch( Exception e ) {
                getLog().error( e );
            }
        } catch( Exception e ) {
            throw new MojoExecutionException( "Could not perform ....", e );
        }
    }
    private void processWithClass( Class clazz ) {
        //do what you want with class from project classpath
    }
}
