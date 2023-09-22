package d.andrade.forum.config

import d.andrade.forum.repository.UsuarioRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.UserDetailsManager

class UserDetailManagerImp(val repository: UsuarioRepository): UserDetailsManager {


    override fun loadUserByUsername(username: String?): UserDetails {
        repository.findByEmail(username).let {
            return User(
                it?.email,
                it?.password,
                it?.roles?.map { r -> SimpleGrantedAuthority(r.name) })
        }
    }

    override fun createUser(user: UserDetails?) {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserDetails?) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(username: String?) {
        TODO("Not yet implemented")
    }

    override fun changePassword(oldPassword: String?, newPassword: String?) {
        TODO("Not yet implemented")
    }

    override fun userExists(username: String?) = repository.existsByEmail(username)
}