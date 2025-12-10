package PComponent_Eva3.PComponent.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import PComponent_Eva3.PComponent.controller.RolesUsuarioControllerV2;
import PComponent_Eva3.PComponent.model.Rol;


@Component
public class RolesUsuarioModelAssembler implements RepresentationModelAssembler<Rol, EntityModel<Rol>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Rol> toModel(Rol rol) {
        return EntityModel.of(rol,
                linkTo(methodOn(RolesUsuarioControllerV2.class).getRoleById(Integer.valueOf(rol.getId()))).withSelfRel(),
                linkTo(methodOn(RolesUsuarioControllerV2.class).getAllRoles()).withRel("roles-usuarios"),
                linkTo(methodOn(RolesUsuarioControllerV2.class).updateRole(Integer.valueOf(rol.getId()), rol)).withRel("actualizar"),
                linkTo(methodOn(RolesUsuarioControllerV2.class).deleteRole(Integer.valueOf(rol.getId()))).withRel("eliminar"),
                linkTo(methodOn(RolesUsuarioControllerV2.class).updatePartialRole(Integer.valueOf(rol.getId()), rol)).withRel("actualizar-parcial")
        );
    }
}