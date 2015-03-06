package basaki.togglz;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.spring.security.SpringSecurityUserProvider;

/**
 * 
 * <tt>VersionTogglzConfiguration</tt> contains all the information in regards
 * to Togglz configuration including the feature enum to be used, how the
 * feature state is persisted, and the way to obtain the current user.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
@Component
public class VersionTogglzConfiguration implements TogglzConfig {

	@Autowired
	private DataSource dataSource;

	/**
	 * Retrieves the Togglz feature enum to be used.
	 * 
	 * @return the feature enum, <tt>VersionFeatures</tt>
	 */
	@Override
	public Class<? extends Feature> getFeatureClass() {
		return VersionFeatures.class;
	}

	/**
	 * Retrieves the persistence repository to be used by Togglz to save the
	 * feature state.
	 * 
	 * @return the database repository used for storing Togglz feature state
	 */
	@Override
	public StateRepository getStateRepository() {
		JDBCStateRepository repository = new JDBCStateRepository(dataSource);

		return repository;
	}

	/**
	 * Retrieves the user provider used for logging on to Togglz console. This
	 * method is only only called once by Togglz.
	 * 
	 * @return the spring security user provider
	 */
	@Override
	public UserProvider getUserProvider() {
		return new SpringSecurityUserProvider("ROLE_USER");
	}

}
