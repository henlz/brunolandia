function PrincipioDiretrizDialogController($scope, $mdDialog, $importService, $mdToast, entidadeExterna) {

    $importService("documentoReferenciaService");

    if (entidadeExterna != null) {
        $scope.entidade = entidadeExterna;
        $scope.modoAlteracao = true;
    } else {
        $scope.entidade = {};
        $scope.modoAlteracao = false;
    }

    $scope.cancelar = function () {
        $mdDialog.cancel();
    };

    /**
     *
     * @returns {boolean}
     */
    $scope.validaForm = function () {
        if (!$scope.estrategiaForm.$valid) {
            $mdToast.show($mdToast.simple()
                .content('Preencha todos os campos obrigatórios!')
                .action('Fechar')
                .highlightAction(false)
                .position('top')).then(function () {
            });
            return false;
        } else {
            return true;
        }
    };

    /**
     *
     */
    $scope.salvar = function () {
        if ($scope.validaForm()) {

            if (!$scope.modoAlteracao) {
                principioDiretrizService.insertPrincipioDiretriz($scope.entidade, {
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
            } else {
                principioDiretrizService.updatePrincipioDiretriz($scope.entidade, {
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
        }
    };

    /**
     *
     */
    documentoReferenciaService.listDocumentosReferenciaByFilters(null, false, {
        callback: function (result) {
            $scope.listaDocumentos = result;
            $scope.$apply();
        }, errorHandler: function (message, exception) {
            $log.error(message);
        }
    });

    /**
     *
     */
    $('html').bind('keypress', function(e)
    {
        if(e.keyCode == 13)
        {
            return false;
        }
    });
}