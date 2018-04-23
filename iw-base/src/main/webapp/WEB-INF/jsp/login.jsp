<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>¡Inicia sesión!</h1>
	<br><br><br>
    <form action="/login" method="post">               
        <fieldset>
           <!-- <legend>Please Login</legend> --> 
           <div class = "rightAl">
            <label for="username">Usuario</label>
            <input type="text" id="username" name="username"/><br><br>
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <br>
            <div class="form-actions">
                <button type="submit" class="btn rightAlBtn">Entrar</button>
            </div>
        </fieldset>
    </form>
	<br><br><br><br><br>
	<div>
	¿Todavía no estás registrado? Regístrate <a>aquí</a>
	</div>		
</div>

<%@ include file="../jspf/footer.jspf"%>