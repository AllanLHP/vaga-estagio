var Client = Client || {};

Client.MaskDate = (function() {

	function MaskDate() {
		this.date = $('.js-mask-date');
	}

	MaskDate.prototype.enable = function() {
		this.date.mask("99/99/9999");
	}

	return MaskDate;

}());

Client.Validate = (function() {

	function Validate() {
		this.form = $('#form_1');
	}

	Validate.prototype.enable = function() {
		this.form.validate({
			rules: {
				name: {
					required: true,
					minlength: 3
				},
				email: {
					required: true
				},
				birthAt: {
					required: true
				},
				phone: {
					required: true
				}
			},
			messages: {
				name: {
					required: "Por favor, informe seu nome",
					minlength: "O nome deve ter pelo menos 3 caracteres"
				},
				email: {
					required: "É necessário informar um email"
				},
				birthAt: {
					required: "Data de Nascimento Obrigatório"
				},
				phone: {
					required: "Telefone Obrigatório"
				},
				region: {
					required: "É necessário selectionar um região"
				}
			}
		});
	}

	return Validate;

}());

Client.NextStep = (function() {

	function NextStep() {
		this.button = $('#next-step');
	}

	NextStep.prototype.init = function() {
		this.button.click(function(event) {
			event.preventDefault();
			var isValid = $("#form_1").valid();
			if (isValid) {
				$(this).parents('#step_1').hide().next().show();
			}
		});
	}

	return NextStep;

}());


Client.SelectRegion = (function() {

	function SelectRegion() {
		this.selectRegion = $('#region');
	}

	SelectRegion.prototype.init = function() {
		this.selectRegion.change(function() {
			var options = [
				"<option value='porto_alegre'>Porto Alegre</option><option value='curitiba'>Curitiba</option>",
				"<option value='sao_paulo'>Sao Paulo</option><option value='rio_janeiro'>Rio de Janeiro</option><option value='belo_horizonte'>Belo Horizonte</option>",
				"<option value='brasilia'>Brasilia</option>",
				"<option value='salvador'>Salvador</option><option value='recife'>Recife</option>",
				"<option value='0'>Não possui disponibilidade</option>"
			];

			var value = $(this).val();
			$("#unity").html(options[value]);
		});
	}

	return SelectRegion;

}());

$(function() {

	var maskDate = new Client.MaskDate();
	maskDate.enable();

	var validate = new Client.Validate();
	validate.enable();

	var nextStep = new Client.NextStep();
	nextStep.init();

	var selectRegion = new Client.SelectRegion();
	selectRegion.init();
});