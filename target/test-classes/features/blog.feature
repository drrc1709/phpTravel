#language: en
#Author: rodriguezcdaniel@yahoo.es
@blog
Feature: Crear un nuevo Post

	@AgregarPost
  Scenario: Agregar un nuevo post a una nueva categoría de blog
    Given que entro a la página de php travels con las credenciales correctas
    When creo una nueva categoría de blog y agrego un nuevo post
    Then veo el post agregado
    