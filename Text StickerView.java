
//var
protected TextSticker textSticker;
public static com.xiaopo.flying.sticker.StickerView stickerView;
stickerView = (com.xiaopo.flying.sticker.StickerView) findViewById(R.id.stickerView);


//xml
 <com.xiaopo.flying.sticker.StickerView
                        android:id="@+id/stickerView"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        app:showBorder="true"
                        app:showIcons="true">

   //center object

   </com.xiaopo.flying.sticker.StickerView>

//call
addTextDialog("", false);

//manage sticker
stickerView.removeAllStickers();
stickerView.setIcons(Arrays.asList(new BitmapStickerIcon[]{}));
stickerView.setBackgroundColor(0);
stickerView.setLocked(true);
stickerView.setConstrained(true);


//method
  public void addTextDialog(String str, boolean isText) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.input_text_di);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        EditText editText = dialog.findViewById(R.id.et_input_txt);
        TextView textView2 = dialog.findViewById(R.id.tv_done);
        TextView textView = dialog.findViewById(R.id.tv_cancel);

        editText.setTypeface(defaultFont);
        editText.setText(str);
        editText.setSelection(editText.getText().length());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 200);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredText = editText.getText().toString().trim();
                if (!enteredText.isEmpty()) {
                    defaultText = enteredText;
                    dialog.dismiss();
                    int currentTextColor = editText.getCurrentTextColor();
                    if (isText) {
                        activityDocumentEditor.ChangeTextSticker(currentTextColor, activityDocumentEditor, activityDocumentEditor.stickerView);
                    } else {
                        activityDocumentEditor.addTextSticker(currentTextColor, activityDocumentEditor, activityDocumentEditor.stickerView);
                    }
                } else {
                    Toast.makeText(Set_Background_Activity.this, "Please Enter Text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }



//code
 public void ChangeTextSticker(int i, Activity activity, com.xiaopo.flying.sticker.StickerView stickerView2) {
        try {
            textSticker = new TextSticker(activity);
            textSticker.setText(defaultText);
            textSticker.setTypeface(defaultFont);
            textSticker.setTextColor(i);
            textSticker.setTextAlign(defaultAlign);
            textSticker.resizeText();
            stickerView2.replace(textSticker);
            stickerView2.invalidate();
        } catch (Exception e) {
            Log.e("TAG", "changeTextSticker: " + e);
            e.printStackTrace();
        }
    }

//code
    public void addTextSticker(int i, Activity activity, com.xiaopo.flying.sticker.StickerView stickerView2) {
        try {
            textSticker = new TextSticker(activity);
            textSticker.setText(defaultText);
            textSticker.setTypeface(defaultFont);
            textSticker.setTextColor(i);
            textSticker.setTextAlign(defaultAlign);
            textSticker.resizeText();
            stickerView2.addSticker(textSticker);
        } catch (Exception e) {
            Log.e("TAG", "addTextSticker: " + e);
            e.printStackTrace();
        }
    }

//code
  stickerView.setOnStickerOperationListener(new com.xiaopo.flying.sticker.StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(Sticker sticker) {

                Log.d("textstickerrrr", "StickerAdded");

            }

            @Override
            public void onStickerClicked(Sticker sticker) {
                if (mstickerView != null) {
                    mstickerView.setInEdit(false);
                }
                mstickerView.setInEdit(false);
               
                if (sticker instanceof DrawableSticker) {

                } else if (sticker instanceof TextSticker) {
                    defaultText = ((TextSticker) sticker).getText();
                    stickerView.replace(sticker);
                    stickerView.invalidate();


                }
                if (!stickerView.bringToFrontCurrentSticker) {
                    stickerView.stickers.remove(sticker);
                    stickerView.stickers.add(sticker);
                }

            }

            @Override
            public void onStickerDeleted(Sticker sticker) {

                if (sticker instanceof TextSticker) {


                }
            }

            @Override
            public void onStickerDragFinished(Sticker sticker) {

            }

            @Override
            public void onStickerEditClicked(@NonNull Sticker sticker) {
                if (mstickerView != null) {
                    mstickerView.setInEdit(false);
                }
                mstickerView.setInEdit(false);
            }

            @Override
            public void onStickerZoomFinished(Sticker sticker) {

            }

            @Override
            public void onStickerFlipped(Sticker sticker) {

            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                if (mstickerView != null) {
                    mstickerView.setInEdit(false);
                }
                mstickerView.setInEdit(false);
            }

            @Override
            public void onStickerDoubleTapped(Sticker sticker) {
                if (mstickerView != null) {
                    mstickerView.setInEdit(false);
                }
                mstickerView.setInEdit(false);
                if (sticker instanceof TextSticker) {
                    addTextDialog(((TextSticker) sticker).getText(), true);
                }
            }
        });
