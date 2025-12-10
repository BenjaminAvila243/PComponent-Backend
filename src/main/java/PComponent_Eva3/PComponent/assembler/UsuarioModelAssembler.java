package PComponent_Eva3.PComponent.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.UsuarioControllerV2;
import PComponent_Eva3.PComponent.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

        @SuppressWarnings("null")
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(Integer.valueOf(usuario.getId()))).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withRel("usuarios"),
                linkTo(methodOn(UsuarioControllerV2.class).updateUsuario(Integer.valueOf(usuario.getId()), usuario)).withRel("actualizar"),
                linkTo(methodOn(UsuarioControllerV2.class).deleteUsuario(Integer.valueOf(usuario.getId()))).withRel("eliminar"),
                linkTo(methodOn(UsuarioControllerV2.class).updatePartialUsuario(Integer.valueOf(usuario.getId()), usuario)).withRel("actualizar-parcial")
        );
    }
}