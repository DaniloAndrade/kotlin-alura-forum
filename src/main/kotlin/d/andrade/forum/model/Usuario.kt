package d.andrade.forum.model

import jakarta.persistence.*

@NamedEntityGraph(
    name = "Usuario.roles",
    attributeNodes = [NamedAttributeNode("roles")]
)
@Entity
data class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,
    @field:ElementCollection()
    @field:CollectionTable(name = "user_roles",
        joinColumns = [JoinColumn( name = "user_id")]
    )
    @field:Column(name= "role")
    @field:Enumerated(EnumType.STRING)
    val roles: Set<Role> = emptySet()
)
