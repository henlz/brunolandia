function ItemSwotDialogController($scope, $mdDialog, $mdToast, entidadeExterna) {

    if (entidadeExterna != null) {
        $scope.entidade = entidadeExterna;
        $scope.modoAlteracao = true;
    } else {
        $scope.entidade = {
            codigo: "",
            descricao: "",
            tipo: ""
        };
        $scope.modoAlteracao = false;
    }

    $scope.cancelar = function() {
        $mdDialog.cancel();
    };

    $scope.validaForm = function() {
        if (!$scope.itemForm.$valid) {
            $mdToast.show($mdToast.simple()
                .content('Preencha todos os campos obrigatórios!')
                .action('Fechar')
                .highlightAction(false)
                .position('top')).then(function() {
            });

            return false;
        } else {
            return true;
        }
    }

    $scope.salvar = function () {
        if ($scope.validaForm()) {
            itemSwotService.insertItemSwot($scope.entidade, {
                callback: function (result) {
                    $mdDialog.hide(result);
                    $scope.$apply();
                },
                errorHandler: function (message, error) {
                    $mdToast.show($mdToast.simple()
                        .content(message)
                        .action('Fechar')
                        .highlightAction(false)
                        .position('bottom left right'))
                        .then(function () {
                        });
                    $log.error(message);
                }
            });
        }
    };

    $('html').bind('keypress', function(e)
    {
        if(e.keyCode == 13)
        {
            return false;
        }
    });
}