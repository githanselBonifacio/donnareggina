function handleComplete(xhr, status, args) {
        if (args.validationFailed) {
            PrimeFaces.debug("Validation Failed");
        } else {
            PrimeFaces.debug("Save:" + args.saved);
            PrimeFaces.debug("userName: " + args.user.userName + ", password: " + args.user.password);
        }
    }